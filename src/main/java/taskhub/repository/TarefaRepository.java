package taskhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import taskhub.domain.tarefa.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Tarefa t WHERE t.projeto.id = :idProjeto")
    void deletarTarefaIdProjeto(Long idProjeto);

    @Modifying
    @Transactional
    @Query("DELETE FROM Tarefa t WHERE t.id = :idTarefa")
    void deletarTarefaIdTarefa(Long idTarefa);
 
    @Query("SELECT t FROM Tarefa t JOIN t.membros m WHERE m.usuario.id = :usuarioId ")
    List<Tarefa> buscarTarefasUsuario(Long usuarioId);

    @Query("SELECT t FROM Tarefa t JOIN t.membros m WHERE m.usuario.id = :idUsuario AND t.id = :idTarefa")
    Tarefa buscarTarefaIdUsuarioId(Long idUsuario, Long idTarefa);
    
}
