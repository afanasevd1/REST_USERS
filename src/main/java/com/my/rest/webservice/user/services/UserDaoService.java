/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.my.rest.webservice.user.services;

import com.my.rest.webservice.user.User;
import com.my.rest.webservice.user.exceptions.NotFoundException;
import com.my.rest.webservice.user.exceptions.UserExistsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Dima", new Date()));
        users.add(new User(2, "Sweta", new Date()));
        users.add(new User(3, "Olga", new Date()));
    }

    public List<User> getAllUsers() {
        return users;
    }

    @ResponseStatus(code = CREATED)
    public User save(User user) {
        for (User eachUser : users) {
            if (Objects.equals(eachUser.getId(), user.getId()))
                throw new UserExistsException("User already exist" + " with id: " + user.getId());
        }

        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundException("id-" + id);
    }

    public void deleteUserById(int id) {
        boolean deleted = users.removeIf(user -> user.getId() == id);
        if (!deleted) throw new NotFoundException("Id not found");
    }

    public void deleteUserById2(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.add(user);
            }
        }
        throw new NotFoundException("id-" + id);
    }

    public void deleteUserById3(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            } else throw new NotFoundException("id-" + id);
        }
    }
}