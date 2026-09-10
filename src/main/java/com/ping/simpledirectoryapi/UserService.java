package com.ping.simpledirectoryapi;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public InMemoryUserStore store;

    public UserService(@Autowired InMemoryUserStore userStore) {
        this.store = userStore;
    }

    public User createUser(String environmentId, User user) {
        user.id = java.util.UUID.randomUUID().toString();
        LOGGER.info("creating user {} in environment {}", user.id, environmentId);
        store.putUser(environmentId, user);
        return user;
    }

    public User getUser(String environmentId, String userId) {
        LOGGER.info("fetching user {} in environment {}", userId, environmentId);
        return store.getUser(environmentId, userId);
    }

    public void deleteUser(String environmentId, String userId) {
        LOGGER.info("deleting user {} in environment {}", userId, environmentId);
        store.removeUser(environmentId, userId);
    }
}
