package taskhub.domain.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
     UserDetails findByLogin(String login);

     @Query("SELECT u FROM Usuario u WHERE u.ativo = true")
     List<Usuario> listaUsuariosAtivos();
}
