package taskhub.domain.colaborador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriacaoColaborador(

    @NotNull
    Long idUsuario,

    @NotNull
    Long idEmpresa, 

    @NotBlank
    String cargo,

    Boolean admin
) {


}
