package taskhub.infra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.security.SecurityFilter;
import taskhub.infra.security.TokenService;
import taskhub.repository.UsuarioRepository;

@Service
public class BuscarUsuarioToken {

    @Autowired
    TokenService tokenService;

    @Autowired
    SecurityFilter securityFilter;
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario usuarioToken(HttpServletRequest request){
        var recuperarToken = securityFilter.recuperarToken(request);
        var login = tokenService.getSubject(recuperarToken);
        var usuario = repository.buscarUsuarioLogin(login);
        return usuario;
    }
}
