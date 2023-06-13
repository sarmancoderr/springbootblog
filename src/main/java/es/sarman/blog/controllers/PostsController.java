package es.sarman.blog.controllers;

import es.sarman.blog.DTO.PostDTO;
import es.sarman.blog.config.JWTUtils;
import es.sarman.blog.entities.PostEntity;
import es.sarman.blog.entities.UserEntity;
import es.sarman.blog.services.PostService;
import es.sarman.blog.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostService postService;
    private final UserService userService;
    private final JWTUtils jwtUtils;

    public PostsController(PostService postService, UserService userService, JWTUtils jwtUtils) {
        this.postService = postService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public PostEntity createPost(@RequestBody PostDTO postDTO, @RequestHeader String authorization) {
        System.out.println(authorization);
        UserEntity author = userService.findUserByUsername(jwtUtils.verify(authorization).getSubject());
        return postService.createPost(postDTO, author);
    }

    @GetMapping
    public List<PostEntity> listPosts() {
        return postService.listPosts();
    }
}
