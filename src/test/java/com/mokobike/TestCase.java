package com.mokobike;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCase {

    @Autowired
    public MockMvc mockMvc;

    public String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoibWFyY2luIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTUzNzIyMzA3OSwiYXV0aG9yaXRpZXMiOlsiQURNSU5fVVNFUiJdLCJqdGkiOiJhMGQwNzVlMy01MTFhLTRhNWYtOTY2NS01MTBmNzMyYTkyZjMiLCJjbGllbnRfaWQiOiJtb2tvIn0.u12mMJ-YIfjwufHjqVNDZqe1oH4jeryils2WRmsQGqM";


    @Before
    public void setUp(){

    }


}
