package taskhub.domain.projeto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAlterarProjeto(
    

    @NotNull
    Long id,

    String nome,

    String descricao,
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataInicio,

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataFinal,

    Prioridade prioridade)
{
}
