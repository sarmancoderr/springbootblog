package es.sarman.blog.config;

import es.sarman.blog.entities.UserEntity;
import es.sarman.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> possibleUser = userRepository.findByUsername(username);
        if (possibleUser.isEmpty()) {
            throw new UsernameNotFoundException("Las credenciales no son correctas");
        }
        UserEntity userEntity = possibleUser.get();
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles("AUTHOR")
                .build();
    }
}
