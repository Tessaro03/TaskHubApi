package taskhub.domain.usuario;


public record DadosListagemUsuario(Long idUsuario, String nome) {
    
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
