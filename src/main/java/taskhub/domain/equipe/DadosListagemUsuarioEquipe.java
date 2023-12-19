package taskhub.domain.equipe;


public record DadosListagemUsuarioEquipe(Long idUsuarioEquipe, String nome, Boolean admin) {
    
    public DadosListagemUsuarioEquipe(Equipe equipe){
        this(equipe.getId(), equipe.getUsuario().getNome(), equipe.getAdmin());
    }
}
 
