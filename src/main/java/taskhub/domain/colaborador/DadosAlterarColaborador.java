package taskhub.domain.colaborador;

import jakarta.validation.constraints.NotNull;

public record DadosAlterarColaborador(

    @NotNull
    Long idColaborador,

    boolean admin,

    String cargo

) {

}
