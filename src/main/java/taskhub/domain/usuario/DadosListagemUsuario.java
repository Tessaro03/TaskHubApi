package taskhub.domain.usuario;


public record DadosListagemUsuario(Long id, String nome, String cargo, String empresa) {
    
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getCargo(), usuario.getEmpresa().getNome());
    }
}
