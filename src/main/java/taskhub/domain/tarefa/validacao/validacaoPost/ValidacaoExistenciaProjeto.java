package taskhub.domain.tarefa.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoExistenciaProjeto implements ValidadorTarefaPost{

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public void validar(DadosCriacaoTarefa dados) {
       var projeto = projetoRepository.getReferenceById(dados.idProjeto());

        if (projeto == null) {
            throw new ValidacaoExcepetion("Projeto não encontrado para o ID: " + dados.idProjeto());
        }
    }
    
}
