package com.mokobike.implementation;

import com.mokobike.domain.Bike;
import com.mokobike.mapper.BikeMapper;
import com.mokobike.repository.BikeRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BikeService implements BikeRepository {

    private static final BikeMapper BIKE_MAPPER = new BikeMapper();
    private static final Logger log = LogManager.getLogger(BikeService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BIKE_BY_ID = "select * from bikes where id = ?";
    private static final String SQL_SELECT_LATEST_BIKE = "select * from bikes order by id desc limit 1";
    private static final String SQL_SELECT_ALL_BIKES = "select * from bikes";
    private static final String SQL_SELECT_ALL_BIKES_PAGINATION = "select * from bikes order by id limit ? offset ?";
    private static final String SQL_SELECT_ALL_BIKES_COUNT = "select count(*) from bikes";
    private static final String SQL_INSERT_INTO_BIKE =
            "insert into bikes(" +
                    "reg_number," +
                    "producer," +
                    "model," +
                    "bike_type," +
                    "bike_condition ," +
                    "rental_price," +
                    "purchase_amount," +
                    "date_of_purchase," +
                    "in_use" +
                    ")values" +
                    "(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_BIKE =
            "update bikes set " +
                    "reg_number = ?," +
                    "producer = ?," +
                    "model = ?," +
                    "bike_type = ?," +
                    "bike_condition = ?," +
                    "rental_price = ?," +
                    "purchase_amount = ?," +
                    "date_of_purchase = ?," +
                    "in_use = ?" +
            "where id = ?";
    private static final String SQL_DELETE_BIKE = "update bikes set bike_condition = 'TRASHED' where id = ?";

    private static final String SQL_SELECT_ACTIVE_BIKES_COUNT = "select count(*) from bikes where bike_type = ? and bike_condition not in ('TRASHED')";

    @Override
    public Bike findByID(Long ID) {
        Bike bike;
        try{
            bike = jdbcTemplate.queryForObject(SQL_SELECT_BIKE_BY_ID, BIKE_MAPPER, ID);
        }catch (EmptyResultDataAccessException e){
            log.error("", e);
            bike = null;
        }
        return bike;
    }

    @Override
    public List<Bike> findAllBikes(int page, int size) {
        int offset = page * size - size;
        return jdbcTemplate.query(SQL_SELECT_ALL_BIKES_PAGINATION, BIKE_MAPPER, size, offset);
    }

    @Override
    public List<Bike> findAllBikes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BIKES, BIKE_MAPPER);
    }

    @Override
    public int bikesCount() {
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_BIKES_COUNT, new Object[]{}, Integer.class);
    }

    @Override
    public void save(Bike bike) {
        jdbcTemplate.update(SQL_INSERT_INTO_BIKE,
                bike.getRegNumber(),
                bike.getProducer(),
                bike.getModel(),
                bike.getType(),
                bike.getCondition(),
                bike.getRentalPrice(),
                bike.getPurchaseAmount(),
                bike.getDateOfPurchase(),
                bike.getInUse()
        );
    }

    @Override
    public Bike update(Bike bike) {

        Bike updatedBike;

        try {
            jdbcTemplate.queryForObject(SQL_SELECT_BIKE_BY_ID, BIKE_MAPPER, bike.getId());

            jdbcTemplate.update(SQL_UPDATE_BIKE,
                    bike.getRegNumber(),
                    bike.getProducer(),
                    bike.getModel(),
                    bike.getType(),
                    bike.getCondition(),
                    bike.getRentalPrice(),
                    bike.getPurchaseAmount(),
                    bike.getDateOfPurchase(),
                    bike.getInUse(),
                    bike.getId()
            );

            updatedBike =  jdbcTemplate.queryForObject(SQL_SELECT_BIKE_BY_ID, BIKE_MAPPER, bike.getId());
        }catch (EmptyResultDataAccessException e){
            log.error("", e);
            updatedBike = null;
        }
        return updatedBike;
    }

    @Override
    public void delete(Long bikeID) {
        jdbcTemplate.update(SQL_DELETE_BIKE, bikeID);
    }

    @Override
    public Bike findLatestBike() {
        return jdbcTemplate.queryForObject(SQL_SELECT_LATEST_BIKE, BIKE_MAPPER);
    }

    @Override
    public Integer findAvailableBikes(String dateFrom, String dateTo, String type) {
        Integer availableBikes;
        try{
            Integer allActiveBikes = jdbcTemplate.queryForObject(SQL_SELECT_ACTIVE_BIKES_COUNT, new Object[]{type},Integer.class);
            String columnName = type.toLowerCase() + "_bike";
            Integer rentBikes = jdbcTemplate.queryForObject("select sum(" + columnName + ") from orders where date_from >= '" + dateFrom +"' and date_to <= '" + dateTo + "'", new Object[]{},Integer.class);

            //if query returns null
            if(rentBikes == null){
                rentBikes = 0;
            }

            availableBikes = allActiveBikes - rentBikes;
        }catch (EmptyResultDataAccessException e){
            log.error("", e);
            availableBikes = 0;
        }

        return availableBikes;
    }

}
