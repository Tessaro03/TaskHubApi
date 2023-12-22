package taskhub.domain.colaborador.validacao.validacaoDelete;

import taskhub.domain.usuario.Usuario;

public interface ValidadorColaboradorDelete {
    
    void validar(Long id, Usuario usuario);
}
