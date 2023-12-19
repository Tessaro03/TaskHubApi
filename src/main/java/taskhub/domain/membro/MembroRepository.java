package taskhub.domain.membro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;


public interface MembroRepository extends JpaRepository<Membro, Long> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Membro m WHERE m.usuario.id = :idUsuario AND m.tarefa.id = :idTarefa")
    boolean buscarUsuarioEmGrupo(Long idUsuario, Long idTarefa);


    @Modifying
    @Transactional
    @Query("DELETE FROM Membro m WHERE m.tarefa.projeto.id = :idProjeto")
    void deletarMembroIdProjeto(Long idProjeto);

    @Modifying
    @Transactional
    @Query("DELETE FROM Membro m WHERE m.tarefa.id = :idTarefa")
    void deletarMembroIdTarefa(Long idTarefa);

    @Modifying
    @Transactional
    @Query("DELETE FROM Membro m WHERE m.usuario.id = :idUsuario AND m.tarefa.projeto.id = :idProjeto")
    void deletarMembroIdEquipe(Long idUsuario, Long idProjeto);

}
