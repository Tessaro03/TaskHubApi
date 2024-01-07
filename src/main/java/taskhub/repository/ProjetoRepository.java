package taskhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import taskhub.domain.projeto.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
    @Query("SELECT p FROM Projeto p JOIN p.equipe e WHERE e.usuario.id = :idUsuario")
    List<Projeto> buscarProjetosUsario(Long idUsuario);

    @Query("SELECT p FROM Projeto p JOIN p.equipe e WHERE e.usuario.id = :idUsuario AND p.id = :idProjeto")
    Projeto buscarProjetoUsario(Long idUsuario, Long idProjeto);

    

}
