package es.sarman.blog.services;

import es.sarman.blog.DTO.PostDTO;
import es.sarman.blog.entities.PostEntity;
import es.sarman.blog.entities.UserEntity;
import es.sarman.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity createPost(PostDTO postDTO, UserEntity user) {
        return postRepository.save(postDTO.toEntity(user));
    }

    public List<PostEntity> listPosts () {
        return postRepository.findAll();
    }
}
