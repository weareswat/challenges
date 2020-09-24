package com.luishipero.interview;

import com.luishipero.interview.model.User;
import com.luishipero.interview.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    private static final String USER_ID_ONE = "1";

    @Mock
    private UserRepository userRepository;
    @Mock
    private List<User> users;

    @Test
    public void getUser() {
        Mockito.when(userRepository.findAllUserBy_id(USER_ID_ONE)).thenReturn(users);

        assertEquals(userRepository.findAllUserBy_id(USER_ID_ONE), users);
    }

}
