package taskhub.domain.membro.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.membro.MembroRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoDeletarMembro implements ValidadorMembroDelete{

    @Autowired
    private MembroRepository membroRepository;

    @Autowired 
    private EquipeRepository equipeRepository;


    @Override
    public void validar(Long idMembro, Usuario usuario) {
        var usuarioDelete = membroRepository.getReferenceById(idMembro);
        var usuarioLogadoFazParteGrupo = membroRepository.buscarUsuarioEmGrupo(usuario.getId(),usuarioDelete.getTarefa().getId());
        var usuarioLogadoEquipe = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), usuarioDelete.getTarefa().getProjeto().getId());
        
        if (usuario.getId() != usuario.getId()) {
            if (usuarioLogadoFazParteGrupo) {
                var usuarioLogadoMembro = membroRepository.buscarMembroIdUsuarioIdTarefa(usuario.getId(), usuarioDelete.getTarefa().getId());
                if (!usuarioLogadoMembro.getAdmin() && !usuarioLogadoEquipe.getAdmin()) {
                    throw new ValidacaoExcepetion("Usuario não tem permissão");
                }
            }
            
            if (!usuarioLogadoEquipe.getAdmin()) {

                throw new ValidacaoExcepetion("Usuario não tem permissão");
            }
        }
    }
        
}
