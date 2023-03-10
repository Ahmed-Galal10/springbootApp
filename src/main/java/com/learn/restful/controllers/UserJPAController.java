package com.learn.restful.controllers;

import com.learn.restful.models.Post;
import com.learn.restful.models.User;
import com.learn.restful.repository.PostRepo;
import com.learn.restful.repository.UserRepo;
import com.learn.restful.utils.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa/users")
public class UserJPAController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Integer userId) {
        Optional<User> user = this.userRepo.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("There is no user found with id %s", userId));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepo.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Integer userId) {
        this.userRepo.deleteById(userId);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByUserId(@PathVariable Integer userId) {
        Optional<User> user = this.userRepo.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("There is no user found with id %s", userId));
        }

        List<Post> posts = user.get().getPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer userId, @Valid @RequestBody Post post) {
        Optional<User> user = this.userRepo.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("There is no user found with id %s", userId));
        }

        post.setUser(user.get());
        Post savedPost = this.postRepo.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
