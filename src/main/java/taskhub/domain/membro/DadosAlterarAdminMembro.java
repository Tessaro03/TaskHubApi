package taskhub.domain.membro;

import jakarta.validation.constraints.NotNull;

public record DadosAlterarAdminMembro(
    @NotNull
    Long idMembro,

    @NotNull
    boolean admin
) {

}
