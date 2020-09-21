package com.luishipero.interview.service;

import com.luishipero.interview.model.User;
import com.luishipero.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Luis Torres
 * @since 09-2020
 */
@Service
public class UserServiceImpl implements UserService {

    public static final int FIRST_ELEMENT = 0;

    @Autowired
    UserRepository userRepository;

    public void saveUser (final User user) {
        userRepository.save(user);
    }

    public List<User> getUserChangesById(final String userId) {
        return userRepository.findAllUserBy_id(userId);
    }

    public Optional<List<User>> getUserChangesByMonth(final String userId, final String month) {
        return userRepository.findAllUserBy_idAndMonth(userId, month);
    }

    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public String getUserFilteredChangesByMonth(final String userId, final String month) {
        List<User> userChangesByMonth;
        StringBuilder stringBuilder = new StringBuilder().append("[");

        if(getUserChangesByMonth(userId, month).isPresent()) {
            userChangesByMonth = getUserChangesByMonth(userId, month).get();
        } else {
            return stringBuilder.append("]").toString();
        }

        /** first record where a change was made for a month */
        User firstOfMonth = userChangesByMonth.get(FIRST_ELEMENT);

        /** if list only contains one element, only one change occurred in the selected month */
        if(userChangesByMonth.size() > 1) {
            // last record where a change was made for a month
            User lastOfMonth = userChangesByMonth.get(userChangesByMonth.size() - 1);
            if(!lastOfMonth.getName().equals(firstOfMonth.getName())) {
                stringBuilder
                    .append("{\"field\": \"name\", \"old\": \"")
                    .append(firstOfMonth.getName())
                    .append("\", \"new\": \"")
                    .append(lastOfMonth.getName())
                    .append("\"}");
            }
            if(!lastOfMonth.getAddress().equals(firstOfMonth.getAddress())) {
                stringBuilder
                        .append("{\"field\": \"address\", \"old\": \"")
                        .append(firstOfMonth.getAddress().getStreet())
                        .append("\", \"new\": \"")
                        .append(lastOfMonth.getAddress().getStreet())
                        .append("\"}");
            }
        }
        return stringBuilder.append("]").toString();
    }
}
