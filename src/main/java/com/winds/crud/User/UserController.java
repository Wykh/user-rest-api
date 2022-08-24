package com.winds.crud.User;

import com.winds.crud.User.models.UserData;
import com.winds.crud.User.models.UserEntity;
import com.winds.crud.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> register(@RequestBody UserData userData) {
        System.out.println(userData);
        return ResponseEntity.ok(userService.register(userData));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable UUID id) {
        return  ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable UUID id, @RequestBody UserData userData) {
        return ResponseEntity.ok(userService.update(id, userData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
