package taskhub.domain.colaborador;

public record DadosListagemColaborador(Long id, String empresa, String usuario, String cargo ,Boolean admin ) {

    public DadosListagemColaborador(Colaborador colaborador){
        this(colaborador.getId(), colaborador.getEmpresa().getNome(), colaborador.getUsuario().getNome(), colaborador.getCargo(), colaborador.getAdmin());
    }
}
