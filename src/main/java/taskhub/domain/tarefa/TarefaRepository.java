package taskhub.domain.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Tarefa t WHERE t.projeto.id = :idProjeto")
    void deletarTarefaIdProjeto(Long idProjeto);

        @Modifying
    @Transactional
    @Query("DELETE FROM Tarefa t WHERE t.id = :idTarefa")
    void deletarTarefaIdTarefa(Long idTarefa);
    
}
