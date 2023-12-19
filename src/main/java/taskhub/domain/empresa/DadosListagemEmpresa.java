package taskhub.domain.empresa;


public record DadosListagemEmpresa(Long id, String nome) {

    public DadosListagemEmpresa(Empresa empresa){
        this(empresa.getId() , empresa.getNome());
    }
}