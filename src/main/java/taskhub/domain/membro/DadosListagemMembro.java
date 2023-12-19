package taskhub.domain.membro;

public record DadosListagemMembro(
    Long idMembro,
    Long idUsuario,
    Long idTarefa,
    Boolean admin
) {
    public DadosListagemMembro(Membro membro){
        this(membro.getId(), membro.getUsuario().getId(), membro.getTarefa().getId(), membro.getAdmin());
    }
}
