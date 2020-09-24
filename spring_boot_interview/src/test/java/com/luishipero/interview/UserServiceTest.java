package com.luishipero.interview;

import com.luishipero.interview.model.User;
import com.luishipero.interview.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    private static final String USER_ID_ONE = "1";

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        List<User> users = userService.getUserChangesById(USER_ID_ONE);

        assertTrue(users instanceof List);
    }
}
