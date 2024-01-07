package taskhub.domain.membro.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.MembroRepository;
import taskhub.repository.TarefaRepository;

@Service
public class ValidacaoUsuarioAdminAdicionarMembro implements ValidadorMembroPostUsuario {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired 
    private TarefaRepository tarefaRepository;

    @Override
    public void validar(Usuario usuario, DadosCriacaoMembro dados) {
        var tarefa = tarefaRepository.getReferenceById( dados.idTarefa());
        var usuarioMembroExiste = membroRepository.buscarUsuarioEmGrupo(usuario.getId(), dados.idTarefa());
        var usuarioEquipe = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), tarefa.getProjeto().getId());

        if (usuarioMembroExiste) {
            var usuarioMembro = membroRepository.buscarMembroIdUsuarioIdTarefa(usuario.getId(), dados.idTarefa());
            if (!usuarioMembro.getAdmin() && !usuarioEquipe.getAdmin()) {
                throw new ValidacaoExcepetion("Usuario não tem permissão");
            }
        }
        else if (!usuarioEquipe.getAdmin()) {
            throw new ValidacaoExcepetion("Usuario não tem permissão");
        }
    }
    
    

}
