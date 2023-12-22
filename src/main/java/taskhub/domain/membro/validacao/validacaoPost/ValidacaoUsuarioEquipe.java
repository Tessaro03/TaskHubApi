package taskhub.domain.membro.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.tarefa.TarefaRepository;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioEquipe implements ValidadorMembroPost{

    @Autowired
    private EquipeRepository repository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void validar(DadosCriacaoMembro dados) {
        var tarefa = tarefaRepository.getReferenceById(dados.idTarefa());
        if (!repository.buscarUsuarioEmGrupo(dados.idUsuario(), tarefa.getProjeto().getId())) {
            throw new ValidacaoExcepetion("Usuario não faz parte da Equipe do Projeto");
            
        }
    }
    
}
