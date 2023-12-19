package taskhub.domain.membro;

import jakarta.validation.constraints.NotNull;

public record DadosCriacaoMembro(
    @NotNull
    Long idUsuario,

    @NotNull 
    Long idTarefa,

    Boolean admin
) {

}
