package com.luishipero.interview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerImplTest {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_TYPE_JSON = "application/json";

    private static final String REGISTER_REQUEST = "/v1/register";
    private static final String JSON_OBJECT = "[{\"_id\": 1,\n" +
            " \"name\": \"Bruce Willis\",\n" +
            "  \"address\": {\"street\": \"My Street\"}, \"month\": \"JULY\"},\n" +
            " {\"_id\": 1,\n" +
            " \"name\": \"Lius Willis\",\n" +
            " \"address\": {\"street\": \"Your Street\"}}]";

    @Autowired
    private MockMvc mvc;

    private static HttpHeaders httpHeaders;

    @Test
    public void registerUsers() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                    .post(REGISTER_REQUEST)
                    .headers(httpHeaders)
                    .content(JSON_OBJECT)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void listUsers() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/user")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void listUserChanges() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/user/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void listUserChangesByMonth() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/user/1?month=june")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @BeforeAll
    private static void initUserListHttpHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(CONTENT_TYPE, APPLICATION_TYPE_JSON);
    }
}
