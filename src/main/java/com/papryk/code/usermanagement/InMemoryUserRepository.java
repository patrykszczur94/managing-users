package com.papryk.code.usermanagement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    private HashMap<Long, UserEntity> map = new HashMap();

    public InMemoryUserRepository() {}

    public InMemoryUserRepository(HashMap<Long, UserEntity> map) {
        this.map = map;
    }

    @Override
    public Collection<UserEntity> findAllUsers() {
        return map.values();
    }

    @Override
    public UserEntity findUserById(Long id) throws UserException {
        Optional<UserEntity> optionalUserEntity = Optional.ofNullable(map.get(id));
        if (!optionalUserEntity.isPresent()) {
            throw new UserException("no user for this id");
        }
        return optionalUserEntity.get();
    }

    @Override
    public UserEntity findUserByName(String name) throws UserException {
        Optional<UserEntity> optionalUserEntity = map.values()
                .stream().filter(user -> user.getName().equals(name))
                .findFirst();
        if (!optionalUserEntity.isPresent()) {
            throw new UserException("no user for this name");
        }
        return optionalUserEntity.get();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        map.put(userEntity.getId(), userEntity);
        return userEntity;
    }

    @Override
    public UserEntity deleteUser(Long id) {
        return map.remove(id);
    }
}
