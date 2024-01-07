package taskhub.domain.tarefa.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.MembroRepository;
import taskhub.repository.TarefaRepository;

@Service
public class ValidacaoAdminDeleteTarefa implements ValidadorTarefaDelete{

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void validar(Long idTarefa, Usuario usuario) {
      var usuarioMembroExiste = membroRepository.buscarUsuarioEmGrupo(usuario.getId(), idTarefa);
        var tarefa = tarefaRepository.getReferenceById(idTarefa);
        var usuarioEquipe = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), tarefa.getProjeto().getId());
        if (!usuarioMembroExiste && !usuarioEquipe.getAdmin()) {
            throw new ValidacaoExcepetion("Usuario não tem permissão");
        }

        if (usuarioMembroExiste) {
            var usuarioMembro = membroRepository.buscarMembroIdUsuarioIdTarefa(usuario.getId(),  tarefa.getId());
            if (!usuarioMembro.getAdmin() && !usuarioEquipe.getAdmin()) {
                throw new ValidacaoExcepetion("Usuario não tem permissão");
            }
        }
    }

}
