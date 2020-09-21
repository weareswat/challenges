package com.luishipero.interview.repository;


import com.luishipero.interview.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Luis Torres
 * @since 09-2020
 */
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAllUserBy_id(final String id);

    Optional<List<User>> findAllUserBy_idAndMonth(final String id, final String month);
}
