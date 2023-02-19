package com.learn.restful.controllers;

import com.learn.restful.daos.UserDao;
import com.learn.restful.models.User;
import com.learn.restful.utils.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer userId) {
        User user = this.userDao.findById(userId);

        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("There is no user found with id %s", userId));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User savedUser = this.userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Integer userId) {
        User user = this.userDao.deleteById(userId);

        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("There is no user found with id %s", userId));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
