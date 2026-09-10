package com.ping.simpledirectoryapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public UserService userService;

    public RateLimiter rateLimiter;

    public UserController(@Autowired UserService userService, @Autowired RateLimiter rateLimiter) {
        this.userService = userService;
        this.rateLimiter = rateLimiter;
    }

    @PostMapping("/environments/{environmentId}/users")
    public ResponseEntity<User> createUser(@PathVariable String environmentId, @RequestBody User user) {
        try {
            rateLimiter.throttle();
        } catch (RateLimitReachedException e) {
            return new ResponseEntity("rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
        }
        System.out.println("creating user " + user.username);
        User created = userService.createUser(environmentId, user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/environments/{environmentId}/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String environmentId, @PathVariable String userId) {
        try {
            rateLimiter.throttle();
        } catch (RateLimitReachedException e) {
            return new ResponseEntity("rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
        }
        User user = userService.getUser(environmentId, userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/environments/{environmentId}/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String environmentId, @PathVariable String userId) {
        try {
            rateLimiter.throttle();
        } catch (RateLimitReachedException e) {
            return new ResponseEntity("rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
        }
        userService.deleteUser(environmentId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
