package taskhub.domain.tarefa.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.membro.MembroRepository;
import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.tarefa.TarefaRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioAdminTarefa implements ValidadorTarefaPatchUsuario{

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void validar(DadosAlterarTarefa dados, Usuario usuario) {

        var usuarioMembroExiste = membroRepository.buscarUsuarioEmGrupo(usuario.getId(), dados.id());
        var tarefa = tarefaRepository.getReferenceById(dados.id());
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
