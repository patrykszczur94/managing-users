package com.papryk.code.usermanagement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> getAllUsers() {
        return userRepository.findAllUsers().stream().map(userEntity -> User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .build())
                .collect(Collectors.toList());
    }

    User getUserById(Long id) throws UserException {
        UserEntity userEntity = userRepository.findUserById(id);
        return User.builder()
                .id(userEntity.getId()).surname(userEntity.getSurname())
                .name(userEntity.getName()).build();
    }

    User getUserByName(String name) throws UserException {
        UserEntity userEntity = userRepository.findUserByName(name);
        return User.builder()
                .id(userEntity.getId()).surname(userEntity.getSurname())
                .name(userEntity.getName()).build();
    }

    User saveUser(User user) {
        userRepository.saveUser(UserEntity.builder().id(user.getId())
                .name(user.getName())
                .surname(user.getSurname()).build());
        return user;
    }

    void updateUser(Long id, User user) throws UserException {
        UserEntity userToUpdate = userRepository.findUserById(id);

        Optional<Long> optionalUserIdToUpdate = Optional.ofNullable(user.getId());
        if (optionalUserIdToUpdate.isPresent()) {
            userToUpdate.setId(optionalUserIdToUpdate.get());
        }

        Optional<String> optionalUserNameToUpdate = Optional.ofNullable(user.getName());
        if (optionalUserNameToUpdate.isPresent()) {
            userToUpdate.setName(optionalUserNameToUpdate.get());
        }

        Optional<String> optionalUserSurnameToUpdate = Optional.ofNullable(user.getSurname());
        if (optionalUserSurnameToUpdate.isPresent()) {
            userToUpdate.setSurname(optionalUserSurnameToUpdate.get());
        }

        userRepository.saveUser(userToUpdate);
    }

    void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
