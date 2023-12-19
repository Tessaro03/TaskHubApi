package taskhub.domain.empresa;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEmpresa(

    @NotBlank
    String nome

) {

}
