package taskhub.domain.equipe.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoEquipeUsuarioAdmin implements ValidadorEquipePostUsuario{

    @Autowired 
    private EquipeRepository equipeRepository;


    @Override
    public void validar(DadosAdicionarUsuario dados, Usuario usuario) {
        var equipes = equipeRepository.equipeProjeto(dados.idProjeto());
        if (!equipes.isEmpty() ) {
            var equipeUsuario = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), dados.idProjeto());
            if (!equipeUsuario.getAdmin()) {
                throw new ValidacaoExcepetion("Usuario não possui permissão");
            }            
        }
    }
    
}
