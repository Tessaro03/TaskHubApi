package taskhub.infra.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;


@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
    try{
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                    .withIssuer("API TaskHub")
                    .withExpiresAt(dataExpiracao())
                    .withSubject(usuario.getLogin())
                    .sign(algoritmo);
                
    } catch (JWTCreationException exception) {
        throw new RuntimeException("Erro ao gerar Token", exception);
    }   
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);

            return JWT.require(algoritmo)
                    .withIssuer("API TaskHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new ValidacaoExcepetion("Token JWT inválido ou expirado!");
        }
    }

}
