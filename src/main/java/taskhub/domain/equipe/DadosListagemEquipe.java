package taskhub.domain.equipe;

import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemEquipe(Long idProjeto, List<DadosListagemUsuarioEquipe> equipe) {

    public DadosListagemEquipe(List<Equipe> equipe){
        this(equipe.get(0).getId(), 
        equipe.stream().map( e -> new DadosListagemUsuarioEquipe(e)).collect(Collectors.toList()));
    }
    

}
