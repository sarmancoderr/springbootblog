package es.sarman.blog.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import es.sarman.blog.DTO.LoginParams;
import es.sarman.blog.config.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Date;

@RestController
public class AuthController {

    private final JWTUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JWTUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login (@RequestBody LoginParams loginParams) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginParams.getUsername(),
                loginParams.getPassword());

        authenticationManager.authenticate(token).isAuthenticated();

        return jwtUtils.createJwt(loginParams.getUsername());
    }

}
