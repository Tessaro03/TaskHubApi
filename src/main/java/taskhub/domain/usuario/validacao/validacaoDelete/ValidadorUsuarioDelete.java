package taskhub.domain.usuario.validacao.validacaoDelete;

import taskhub.domain.usuario.Usuario;

public interface ValidadorUsuarioDelete {
    
    void validar(Long id, Usuario usuario);
}
