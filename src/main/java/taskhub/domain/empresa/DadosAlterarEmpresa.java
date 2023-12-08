package taskhub.domain.empresa;

import jakarta.validation.constraints.NotNull;

public record DadosAlterarEmpresa(

    @NotNull
    Long id,    
    String nome
) {

}
