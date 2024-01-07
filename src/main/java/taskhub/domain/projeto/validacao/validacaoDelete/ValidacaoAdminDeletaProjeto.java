package taskhub.domain.projeto.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@Service
public class ValidacaoAdminDeletaProjeto implements ValidadorProjetoDelete {

    @Autowired 
    private EquipeRepository equipeRepository;

 

    @Override
    public void validar(Usuario usuario, Long idProjeto) {
        var usuarioEquipe = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), idProjeto);
        if (!usuarioEquipe.getAdmin()) {
            throw new ValidacaoExcepetion("Usuario não possui permissão");
        }
    }
    

    
}
