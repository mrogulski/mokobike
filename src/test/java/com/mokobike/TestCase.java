package com.mokobike;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yaml")
public class TestCase {

    @Autowired
    public MockMvc mockMvc;

    public String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoibWFyY2luIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTUzODE2MDY4NCwiYXV0aG9yaXRpZXMiOlsiQURNSU5fVVNFUiJdLCJqdGkiOiJmZTU0MjU0Yy1lMGQ0LTRlNTMtYWJkYy0xNmIwYjY0MjBhMGQiLCJjbGllbnRfaWQiOiJtb2tvIn0.y4djO7-nTNAI6SJiWPmf72VSJHukOEB34WFIj0KKKwA";

    @Before
    public void setUp(){

    }


}
