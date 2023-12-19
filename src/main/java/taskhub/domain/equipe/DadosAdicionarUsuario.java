package taskhub.domain.equipe;


import jakarta.validation.constraints.NotNull;

public record DadosAdicionarUsuario(
    

    @NotNull
    Long idProjeto,

    @NotNull
    Long idUsuario,

    @NotNull
    Boolean admin

) {

}
