package com.winds.crud.User.service;

import com.winds.crud.User.models.UserData;
import com.winds.crud.User.models.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public UserEntity register(UserData userData);
    public UserEntity get(UUID id);
    public List<UserEntity> getAll();
    public UserEntity update(UUID id, UserData userData);
    public UserEntity delete(UUID id);
}
