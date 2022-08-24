package com.winds.crud.User.service;

import com.winds.crud.User.UserRepository;
import com.winds.crud.User.models.UserData;
import com.winds.crud.User.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity register(UserData userData) {
        if (!isPhoneGood(userData))
            throw new RuntimeException("Phone number has incorrect format");
        if (!isEmailGood(userData))
            throw  new RuntimeException("Email has incorrect format");
        return userRepository.save(UserEntity.of(userData));
    }

    @Override
    public UserEntity get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found by id"));
    }

    @Override
    public UserEntity update(UUID id, UserData userData) {
        UserEntity foundEntity = get(id);
        if (userData.getFirstName() != null) {
            foundEntity.setFirstName(userData.getFirstName());
        }
        if (userData.getLastName() != null) {
            foundEntity.setLastName(userData.getLastName());
        }
        if (userData.getPatronymic() != null) {
            foundEntity.setPatronymic(userData.getPatronymic());
        }
        if (userData.getPhone() != null) {
            foundEntity.setPhone(userData.getPhone());
        }
        if (userData.getEmail() != null) {
            foundEntity.setEmail(userData.getEmail());
        }
        return userRepository.save(foundEntity);
    }

    @Override
    public UserEntity delete(UUID id) {
        UserEntity foundUser = get(id);
        userRepository.delete(foundUser);
        return foundUser;
    }

    private boolean isEmailGood(UserData userData) {
        return true;
    }

    private boolean isPhoneGood(UserData userData) {
        return true;
    }
}
