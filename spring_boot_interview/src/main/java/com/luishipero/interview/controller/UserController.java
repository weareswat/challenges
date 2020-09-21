package com.luishipero.interview.controller;

import com.luishipero.interview.model.User;
import java.util.List;

/**
 * @author Luis Torres
 * @since 09-2020
 */
public interface UserController {

    List<User> registerChanges(final List<User> users);

    String listChanges(final String identifier, final String month);

    List<User> getUserChangesById(final String identifier);

    List<User> getAllUser();
}
