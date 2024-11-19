package com.posco.standardmanagement.s20a01.service;

import com.posco.standardmanagement.s20a01.domain.user.CreateUserCommand;
import com.posco.standardmanagement.s20a01.domain.user.DeleteUserCommand;
import com.posco.standardmanagement.s20a01.domain.user.UpdateUserCommand;
import com.posco.standardmanagement.s20a01.domain.user.User;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class UserController {

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public UserController(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepositoryService.findAll());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> create(
        @Valid @RequestBody CreateUserCommand command
    ) {
        return ResponseEntity.ok(userRepositoryService.create(command));
    }

    @PatchMapping(path = "users/{id}")
    public ResponseEntity<User> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserCommand command
    ) {
        return ResponseEntity.ok(userRepositoryService.update(id, command));
    }

    @DeleteMapping(path = "users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepositoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
