package es.sarman.blog.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JWTUtils {
    private final TokenConfigProperties tokenConfigProperties;
    private final Algorithm algorithm;

    @Autowired
    public JWTUtils(TokenConfigProperties tokenConfigProperties) {
        this.tokenConfigProperties = tokenConfigProperties;
        this.algorithm = Algorithm.HMAC256(tokenConfigProperties.getSecret());
    }

    public String createJwt(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(tokenConfigProperties.getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date().toInstant().plus(Duration.ofDays(tokenConfigProperties.getExpiration())))
                .sign(algorithm);
    }

}
