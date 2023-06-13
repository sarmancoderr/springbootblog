package es.sarman.blog.services;

import es.sarman.blog.entities.UserEntity;
import es.sarman.blog.exceptions.ExistingUserException;
import es.sarman.blog.exceptions.UserNotFoundException;
import es.sarman.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser (UserEntity userEntity) {
        userRepository.findByUsername(userEntity.getUsername()).ifPresent(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) {
                throw new ExistingUserException();
            }
        });

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

}
