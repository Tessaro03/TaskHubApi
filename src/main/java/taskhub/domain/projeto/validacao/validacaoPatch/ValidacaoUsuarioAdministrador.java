package taskhub.domain.projeto.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@Service
public class ValidacaoUsuarioAdministrador implements ValidadorProjetoPatchUsuario{


    @Autowired
    private EquipeRepository equipeRepository;


    @Override
    public void validar(Usuario usuario, DadosAlterarProjeto dados) {
      var equipeUsuario = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), dados.id());
      if (!equipeUsuario.getAdmin()) {
        throw new ValidacaoExcepetion("Usuario não possui permissão");
      }
    }

   

    
}