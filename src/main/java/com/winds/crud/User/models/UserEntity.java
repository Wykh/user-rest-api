package com.winds.crud.User.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String phone;

    private String email;

    public static UserEntity of(UserData userData) {
        return UserEntity.builder()
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .patronymic(userData.getPatronymic())
                .phone(userData.getPhone())
                .email(userData.getEmail())
                .build();
    }
}
