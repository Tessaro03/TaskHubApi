package taskhub.domain.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import taskhub.domain.projeto.Prioridade;

public record DadosCriacaoTarefa(
    
    @NotNull
    Long idProjeto,

    @NotBlank
    String nome,

    String descricao,
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataInicio,

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataFinal,

    Prioridade prioridade
) {

}
