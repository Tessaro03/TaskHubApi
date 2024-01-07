package taskhub.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import taskhub.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
     UserDetails findByLogin(String login);

     @Query("SELECT u FROM Usuario u WHERE u.login = :login")
     Usuario buscarUsuarioLogin(String login);

}
