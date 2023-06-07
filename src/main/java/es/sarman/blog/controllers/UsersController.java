package es.sarman.blog.controllers;

import es.sarman.blog.entities.UserEntity;
import es.sarman.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createUser (@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<UserEntity> findAllUsers () {
        return userService.listUsers();
    }

}
