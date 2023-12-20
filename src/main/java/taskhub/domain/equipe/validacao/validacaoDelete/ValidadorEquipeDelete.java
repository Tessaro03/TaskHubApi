package taskhub.domain.equipe.validacao.validacaoDelete;

import taskhub.domain.usuario.Usuario;

public interface ValidadorEquipeDelete {
    
    void validar(Long id, Usuario usuario);
}
