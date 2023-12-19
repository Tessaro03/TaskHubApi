package taskhub.domain.projeto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCriacaoProjeto(
    
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
