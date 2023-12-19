package taskhub.domain.equipe;

import jakarta.validation.constraints.NotNull;

public record DadosAlterarAdminEquipe(

    @NotNull
    Long idEquipe,

    @NotNull
    boolean admin
) {

}
