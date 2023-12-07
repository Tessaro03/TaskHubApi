package taskhub.domain.empresa;

import java.util.List;
import java.util.stream.Collectors;

import taskhub.domain.usuario.DadosListagemUsuario;

public record DadosListagemEmpresa(Long id, String nome,String proprietario ,List<DadosListagemUsuario> colaboradores) {

    public DadosListagemEmpresa(Empresa empresa){
        this(empresa.getId() , empresa.getNome(), empresa.getProprietario().getNome(),
             empresa.getColaboradores().stream()
                   .map(DadosListagemUsuario::new)
                   .collect(Collectors.toList()));
    }
}