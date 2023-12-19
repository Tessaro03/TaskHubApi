package taskhub.domain.tarefa.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoDataTarefaComProjeto implements ValidadorTarefa{

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public void validar(DadosCriacaoTarefa dados) {
        var projeto = projetoRepository.getReferenceById(dados.idProjeto());


        // Verifica se a data de início da tarefa é antes da data de início do projeto
        if (dados.dataInicio().isBefore(projeto.getDataInicio())) {
            throw new ValidacaoExcepetion("A data de início da tarefa não pode ser anterior à data de início do projeto.");
        }

        // Verifica se a data de início da tarefa é após a data final do projeto
        if (projeto.getDataFinal() != null && dados.dataInicio().isAfter(projeto.getDataFinal())) {
            throw new ValidacaoExcepetion("A data de início da tarefa não pode ser posterior à data final do projeto.");
        }

        // Verifica se a data de fim da tarefa é posterior à data final do projeto
        if (projeto.getDataFinal() != null && dados.dataFinal().isAfter(projeto.getDataFinal())) {
            throw new ValidacaoExcepetion("A data de fim da tarefa não pode ser posterior à data final do projeto.");
        }
}
    
}
