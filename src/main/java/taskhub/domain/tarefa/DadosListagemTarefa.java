package taskhub.domain.tarefa;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import taskhub.domain.membro.DadosListagemMembro;
import taskhub.domain.projeto.Prioridade;

public record DadosListagemTarefa( 
    Long idTarefa,    
    String nome,
    String descricao,
    LocalDate dataInicio,
    LocalDate dataFinal,
    String status,
    Prioridade prioridade,
    List<DadosListagemMembro> membros
) 
{
    public DadosListagemTarefa(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), tarefa.getDataInicio(),
        tarefa.getDataFinal() , tarefa.getStatus(), tarefa.getPrioridade(),
        tarefa.getMembros().stream().map(DadosListagemMembro::new).collect(Collectors.toList()));
    }

} 

