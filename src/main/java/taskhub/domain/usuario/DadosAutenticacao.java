package taskhub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

    @NotBlank
    String login,

    @NotBlank
    String senha
) {

}
