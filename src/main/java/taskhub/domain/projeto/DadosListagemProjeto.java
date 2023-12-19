package taskhub.domain.projeto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import taskhub.domain.equipe.DadosListagemUsuarioEquipe;
import taskhub.domain.tarefa.DadosListagemTarefa;

public record DadosListagemProjeto(
    Long idProjeto,    
    String nome,
    String descricao,
    LocalDate dataInicio,
    LocalDate dataFinal,
    String status,
    Prioridade prioridade,
    List<DadosListagemUsuarioEquipe> equipe,
    List<DadosListagemTarefa> tarefas

)
{
    public DadosListagemProjeto(Projeto projeto){
        this(projeto.getId(), projeto.getNome(), projeto.getDescricao(), projeto.getDataInicio(),
        projeto.getDataFinal() , projeto.getStatus(), projeto.getPrioridade(),
        projeto.getEquipe().stream().map(DadosListagemUsuarioEquipe::new).collect(Collectors.toList()),
        projeto.getTarefas().stream().map(DadosListagemTarefa::new).collect(Collectors.toList()));
    
    }
}
