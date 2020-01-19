package com.papryk.code.usermanagement;

import java.util.Collection;

interface UserRepository {
    Collection<UserEntity> findAllUsers();

    UserEntity findUserById(Long id) throws UserException;

    UserEntity findUserByName(String name) throws UserException;

    UserEntity saveUser(UserEntity userEntity);

    UserEntity deleteUser(Long id);
}
