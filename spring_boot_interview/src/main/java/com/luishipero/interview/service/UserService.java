package com.luishipero.interview.service;

import com.luishipero.interview.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser (final User user);

    List<User> getUserChangesById(final String userId);

    Optional<List<User>> getUserChangesByMonth(final String userid, final String month);

    List<User> getAllUser();

    String getUserFilteredChangesByMonth(final String userId, final String month);
}
