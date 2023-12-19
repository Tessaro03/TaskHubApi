package taskhub.domain.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import taskhub.domain.projeto.Prioridade;

public record DadosAlterarTarefa(

    @NotNull
    Long id,

    String nome,

    String descricao,
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataInicio,

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataFinal,

    Prioridade prioridade,

    String status
    

    ) {

}
