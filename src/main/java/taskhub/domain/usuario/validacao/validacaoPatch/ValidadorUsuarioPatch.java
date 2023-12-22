package taskhub.domain.usuario.validacao.validacaoPatch;

import taskhub.domain.usuario.DadosAlterarUsuario;
import taskhub.domain.usuario.Usuario;

public interface ValidadorUsuarioPatch {
    
    void validar(DadosAlterarUsuario dados, Usuario usuario);
}
