package com.luishipero.interview.controller;

import com.luishipero.interview.model.User;
import com.luishipero.interview.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;

/**
 * @author Luis Torres
 * @since 09-2020
 */
@RestController
@RequestMapping("/v1/")
public class UserControllerImpl implements UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * Registers all user changes.
     *
     * @param users JSON object containing all user name or address changes.
     * @return
     */
    @PostMapping(value = "register",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> registerChanges(@RequestBody final List<User> users) {
        return users;
    }

    /**
     * Lists all existing user changes (name and/or address) of a specific user, filtered by month.
     *
     * @param identifier user ID
     * @param month required to do the filter by month
     * @return
     */
    @GetMapping(value = "list/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String listChanges(@PathVariable(value = "id", required = true) final String identifier,
                              @RequestParam("month") final String month) {
        return userService.getUserFilteredChangesByMonth(identifier, month.toUpperCase());
    }

    /**
     * Lists all existing user changes (name and/or address) of a specific user.
     *
     * @param identifier user ID
     * @return list with all changes by userId.
     */
    @GetMapping(value = "user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUserChangesById(@PathVariable(value = "id", required = true) final String identifier) {
        return userService.getUserChangesById(identifier);
    }

    /**
     * Lists all existing user changes (name and/or address) of all users.
     *
     * @return list with all changes.
     */
    @GetMapping(value = "user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
