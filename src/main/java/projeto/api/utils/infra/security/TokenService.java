package projeto.api.utils.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.infra.exception.TokenValidationException;
import projeto.api.utils.infra.exception.ValidationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generate(UserDetails user) {
        Long userId = ((User)user).getId();
        Instant expiration = LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuer("Utils API")
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    public String validTokenAndGetSubject(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Utils API")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (Exception e) {
            throw new TokenValidationException("Invalid or expired token");
        }
    }
}
