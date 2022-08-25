package com.winds.crud.User;

import com.winds.crud.User.models.UserData;
import com.winds.crud.User.models.UserEntity;
import com.winds.crud.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Tag(name = "Create")
    @Operation(summary = "Create user in the database and return him")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserEntity.class)
            ),
            description = "Create user in the database and return him"
    )
    @PostMapping
    public ResponseEntity<UserEntity> register(@RequestBody UserData userData) {
        System.out.println(userData);
        return ResponseEntity.ok(userService.register(userData));
    }

    @Tag(name = "Get")
    @Operation(summary = "Get user by ID")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserEntity.class)
            ),
            description = "Get user by ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @Tag(name = "Get")
    @Operation(summary = "Get all existing users")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = UserEntity.class))
            ),
            description = "Get all existing users"
    )
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Tag(name = "Update")
    @Operation(summary = "Update user by ID")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserEntity.class)
            ),
            description = "Update user by ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable UUID id, @RequestBody UserData userData) {
        return ResponseEntity.ok(userService.update(id, userData));
    }

    @Tag(name = "Delete")
    @Operation(summary = "Delete user by ID")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserEntity.class)
            ),
            description = "Delete user by ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
