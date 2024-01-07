package taskhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import taskhub.domain.equipe.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {


    @Query("SELECT e FROM Equipe e WHERE e.projeto.id = :id")
    List<Equipe> equipeProjeto(Long id);
    
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Equipe e WHERE e.usuario.id = :idUsuario AND e.projeto.id = :idProjeto")
    Boolean buscarUsuarioEmGrupo(Long idUsuario, Long idProjeto);

    @Modifying
    @Transactional
    @Query("DELETE FROM Equipe e WHERE e.projeto.id = :idProjeto")
    void deletarEquipeIdProjeto(Long idProjeto);


    @Modifying
    @Transactional
    @Query("DELETE FROM Equipe e WHERE e.id = :idEquipe")
    void deletarEquipeIdEquipe(Long idEquipe);

    @Query("SELECT e FROM Equipe e WHERE e.usuario.id = :idUsuario AND e.projeto.id = :idProjeto")
    Equipe buscarEquipeIdUsuarioIdProjeto(Long idUsuario, Long idProjeto);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Equipe e WHERE e.projeto.id = :idProjeto")
    Boolean existeEquipePorIdProjeto(Long idProjeto);
}
