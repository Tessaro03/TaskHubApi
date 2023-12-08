package taskhub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAlterarUsuario(

    @NotNull
    Long id,
    String nome,
    String cargo,
    String senha
) {


}
