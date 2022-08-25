package com.winds.crud.User.service;

import com.winds.crud.User.UserRepository;
import com.winds.crud.User.models.UserData;
import com.winds.crud.User.models.UserEntity;
import com.winds.crud.exceptions.IncorrectEmailException;
import com.winds.crud.exceptions.IncorrectPhoneNumberException;
import com.winds.crud.exceptions.UserNotFoundInDatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity register(UserData userData) {
        if (!isPhoneGood(userData))
            throw new IncorrectPhoneNumberException("Phone number has incorrect format");
        if (!isEmailGood(userData))
            throw  new IncorrectEmailException("Email has incorrect format");
        return userRepository.save(UserEntity.of(userData));
    }

    @Override
    public UserEntity get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundInDatabaseException("File not found by id"));
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
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
        if (userData.getPhone() != null && isPhoneGood(userData)) {
            foundEntity.setPhone(userData.getPhone());
        }
        if (userData.getEmail() != null && isEmailGood(userData)) {
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

    public static boolean patternMatches(String matcher, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(matcher)
                .matches();
    }

    private boolean isEmailGood(UserData userData) {
        String emailRegex = "^.+@\\S+\\.\\S+$";
        return patternMatches(userData.getEmail(), emailRegex);
    }

    private boolean isPhoneGood(UserData userData) {
        String numberRegex = "^\\+\\d{11}$";
        return patternMatches(userData.getPhone(), numberRegex);
    }
}
