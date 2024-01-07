
package taskhub.domain.equipe.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@Service
public class ValidacaoEquipeUsuarioAdmin implements ValidadorEquipePostUsuario{

    @Autowired 
    private EquipeRepository equipeRepository;


    @Override
    public void validar(DadosAdicionarUsuario dados, Usuario usuario) {
        var equipes = equipeRepository.existeEquipePorIdProjeto(dados.idProjeto());
        if (equipes) {
            var equipeUsuario = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), dados.idProjeto());
            if (!equipeUsuario.getAdmin()) {
                throw new ValidacaoExcepetion("Usuario não possui permissão");
            }            
        }
    }
    
}
