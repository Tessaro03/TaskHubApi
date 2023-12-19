package taskhub.infra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.membro.MembroRepository;
import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.tarefa.TarefaRepository;

@Service
public class DeleteEntidades {
    
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private EquipeRepository equipeRepository;


    public void deletarProjeto(Long idProjeto){
        membroRepository.deletarMembroIdProjeto(idProjeto);
        tarefaRepository.deletarTarefaIdProjeto(idProjeto);
        equipeRepository.deletarEquipeIdProjeto(idProjeto);
        projetoRepository.deleteById(idProjeto);
    }

    public void deletarTarefa(Long idTarefa){
        membroRepository.deletarMembroIdTarefa(idTarefa);
        tarefaRepository.deletarTarefaIdTarefa(idTarefa);
    }

    public void deletarEquipe(Long idEquipe){
        var equipe = equipeRepository.getReferenceById(idEquipe);

        membroRepository.deletarMembroIdEquipe(equipe.getUsuario().getId(), equipe.getProjeto().getId());
        equipeRepository.deletarEquipeIdEquipe(idEquipe);
    }
}
