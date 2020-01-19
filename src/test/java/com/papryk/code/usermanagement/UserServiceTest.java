package com.papryk.code.usermanagement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

class UserServiceTest {

    private HashMap<Long, UserEntity> map = new HashMap();

    private UserService userService;

    @BeforeEach
    public void init() {
        map.put(100L, UserEntity.builder().id(100L).name("name").surname("surname").build());
        map.put(101L, UserEntity.builder().id(101L).name("name1").surname("surname1").build());
        userService = new UserService(new InMemoryUserRepository(map));
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        Assertions.assertThat(allUsers.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnSelectedUserById() throws UserException {
        User user = userService.getUserById(100L);
        Assertions.assertThat(user).isEqualTo(getUser());
    }

    @Test
    public void shouldReturnSelectedUserByName() throws UserException {
        User user = userService.getUserByName("name");
        Assertions.assertThat(user).isEqualTo(getUser());
    }

    @Test
    public void shouldAddNewUser() throws UserException {
        userService.saveUser(getNewUser());
        User user = userService.getUserById(102L);
        Assertions.assertThat(user).isEqualTo(getNewUser());
    }

    @Test
    public void shouldUpdateUser() throws UserException {
        userService.updateUser(100L, getUserToUpdate());
        User user = userService.getUserById(100L);
        Assertions.assertThat(user).isEqualTo(getUserToUpdate());
    }

    @Test
    public void shouldRemoveUser() throws UserException {
        userService.deleteUser(100L);
        List<User> allUsers = userService.getAllUsers();
        Assertions.assertThat(allUsers.size()).isEqualTo(1);
    }

    private User getUserToUpdate() {
        return User.builder().id(102L).name("nameUpdated").surname("surnameUpdated").build();
    }

    private User getNewUser() {
        return User.builder().id(102L).name("namenew").surname("surnamenew").build();
    }

    private User getUser() {
        return User.builder().id(100L).name("name").surname("surname").build();
    }
}