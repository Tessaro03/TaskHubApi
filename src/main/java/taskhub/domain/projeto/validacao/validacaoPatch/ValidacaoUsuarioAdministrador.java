package taskhub.domain.projeto.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioAdministrador implements ValidadorProjetoPatchUsuario{


    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void validar(Usuario usuario) {
        var equipeUsuario = equipeRepository.buscarEquipeIdUsuario(usuario.getId());
      if (!equipeUsuario.getAdmin()) {
        throw new ValidacaoExcepetion("Usuario não possui permissão");
      }
    }

   

    
}