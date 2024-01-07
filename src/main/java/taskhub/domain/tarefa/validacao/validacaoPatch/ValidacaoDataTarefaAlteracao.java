package taskhub.domain.tarefa.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ProjetoRepository;
import taskhub.repository.TarefaRepository;

@Service
public class ValidacaoDataTarefaAlteracao implements ValidadorTarefaPatch {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void validar(DadosAlterarTarefa dados) {
        var tarefa = tarefaRepository.getReferenceById(dados.id());
        var projeto = projetoRepository.getReferenceById(tarefa.getProjeto().getId());

        var dataInicioProjeto = projeto.getDataInicio();
        var dataFinalProjeto = projeto.getDataFinal();

        var dataFinal = projeto.getDataFinal();
        var dataInicio = projeto.getDataInicio();

        if (dados.dataFinal() != null) {
            dataFinal = dados.dataFinal();    
        }
        if (dados.dataInicio() != null) {
            dataInicio = dados.dataInicio();
        }

        if ( dataFinal.isBefore(dataInicio)){
            throw new ValidacaoExcepetion("O Fim da Tarefa não pode ser antes do inicio  ");
        }

        if (dataFinal.isAfter(dataFinalProjeto)) {
            throw new ValidacaoExcepetion("O Fim da Tarefa não pode ser depois do Final Projeto ");
        }
        
        if (dataInicio.isBefore(dataInicioProjeto)) {
            throw new ValidacaoExcepetion("O Inicio da Tarefa não pode ser antes do inicio Projeto ");
        }
    }
    

}
