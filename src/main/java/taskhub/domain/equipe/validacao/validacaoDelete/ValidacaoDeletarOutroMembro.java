package taskhub.domain.equipe.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;
import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoDeletarOutroMembro implements ValidadorEquipeDelete{

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void validar(Long id, Usuario usuarioLogado) {
        var equipeUsuarioDeletado = equipeRepository.getReferenceById(id);
        var equipeUsuarioLogado = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuarioLogado.getId(), equipeUsuarioDeletado.getProjeto().getId());
        if (equipeUsuarioDeletado.getUsuario().getId() != usuarioLogado.getId() && !equipeUsuarioLogado.getAdmin()) {
            throw new ValidacaoExcepetion("Usuario não possui permissão para deletar");
        }
    }
    
}
