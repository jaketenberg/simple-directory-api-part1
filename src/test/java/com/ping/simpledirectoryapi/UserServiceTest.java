package com.ping.simpledirectoryapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Test
    void testCreateUserAssignsIdAndStoresUser() {
        UserService userService = new UserService(new InMemoryUserStore());
        User user = new User();
        user.username = "jdoe";

        User created = userService.createUser("env-1", user);

        assertNotNull(created.id);
        assertEquals("jdoe", created.username);
    }

    @Test
    void testGetUserReturnsStoredUser() {
        UserService userService = new UserService(new InMemoryUserStore());
        User user = new User();
        user.username = "jdoe";
        User created = userService.createUser("env-1", user);

        User fetched = userService.getUser("env-1", created.id);

        assertEquals(created.id, fetched.id);
    }
}
