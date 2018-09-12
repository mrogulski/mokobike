package com.mokobike.domain;

import java.util.Objects;

public class Role {

    private Long id;
    private String roleName;
    private String description;

    public Role(Long id, String roleName, String description){
        this.id = id;
        this.roleName = roleName;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                Objects.equals(getRoleName(), role.getRoleName()) &&
                Objects.equals(getDescription(), role.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getRoleName(), getDescription());
    }
}

