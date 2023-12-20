package taskhub.domain.equipe.validacao.validacaoPost;

import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.usuario.Usuario;

public interface ValidadorEquipePostUsuario {
    
      void validar(DadosAdicionarUsuario dados ,Usuario usuario);
}
