package com.winds.crud.service;

import com.winds.crud.User.UserRepository;
import com.winds.crud.User.models.UserData;
import com.winds.crud.User.models.UserEntity;
import com.winds.crud.User.service.UserServiceImpl;
import com.winds.crud.exceptions.IncorrectEmailException;
import com.winds.crud.exceptions.IncorrectPhoneNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    UUID hardcodedId = UUID.fromString("1-1-1-1-1");

    @Test
    void registerSuccess_shouldSaveUser_whenDataIsOk() {
        //arrange
        String firstName = "Artyom";
        String lastName = "Ivanov";
        String patronymic = "Ivanovich";
        String phone = "+79139493004";
        String email = "Art@gmail.com";
        UserData userData = new UserData(firstName, lastName, patronymic, phone, email);
        UserEntity expectedUserEntity = new UserEntity(hardcodedId, firstName, lastName, patronymic, phone, email);
        when(userRepository.save(any())).thenReturn(UserEntity.of(userData));

        //act
        UserEntity actualUserEntity = userService.register(userData);
        actualUserEntity.setId(hardcodedId);

        //assert
        assertThat(actualUserEntity).
                usingRecursiveComparison()
                .isEqualTo(expectedUserEntity);
    }

    @Test
    void registerFail_shouldThrowException_whenPhoneIsIncorrect() {
        //arrange
        String firstName = "Artyom";
        String lastName = "Ivanov";
        String patronymic = "Ivanovich";
        String phone = "+7913";
        String email = "Art@gmail.com";
        UserData userData = new UserData(firstName, lastName, patronymic, phone, email);

        //act
        //assert
        assertThatExceptionOfType(IncorrectPhoneNumberException.class)
                .isThrownBy(() -> userService.register(userData));

    }

    @Test
    void registerFail_shouldThrowException_whenEmailIsIncorrect() {
        //arrange
        String firstName = "Artyom";
        String lastName = "Ivanov";
        String patronymic = "Ivanovich";
        String phone = "+79519405004";
        String email = "Artgmail";
        UserData userData = new UserData(firstName, lastName, patronymic, phone, email);

        //act
        //assert
        assertThatExceptionOfType(IncorrectEmailException.class)
                .isThrownBy(() -> userService.register(userData));

    }

    @Test
    void updateSuccess_shouldUpdateNotNullableFields_whenThereAreNullableFields() {
        //arrange
        String firstName = "Artyom";
        String lastName = "Ivanov";
        String patronymic = "Ivanovich";
        String phone = "+79139493004";
        String email = "Art@gmail.com";

        String newPhone = "+79519405004";
        String newEmail = "Bibletum@mail.ru";

        UserEntity foundUserEntity = new UserEntity(hardcodedId, firstName, lastName, patronymic, phone, email);
        UserData newUserData =  new UserData(null, null, null, newPhone, newEmail);
        UserEntity expectedUserEntity = new UserEntity(hardcodedId, firstName, lastName, patronymic, newPhone, newEmail);

        when(userRepository.findById(hardcodedId)).thenReturn(Optional.of(foundUserEntity));
        when(userRepository.save(expectedUserEntity)).thenReturn(expectedUserEntity);

        //act
        UserEntity updatedUserEntity = userService.update(hardcodedId, newUserData);

        //assert
        assertThat(updatedUserEntity).
                usingRecursiveComparison()
                .isEqualTo(expectedUserEntity);
    }
}
