package taskhub.domain.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEmpresa(

    @NotBlank
    String nome,

    @NotNull
    Long idUsuario
) {

}
