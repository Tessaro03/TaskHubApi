package taskhub.domain.membro.validacao.validacaoDelete;

import taskhub.domain.usuario.Usuario;

public interface ValidadorMembroDelete {
    
  void validar(Long id, Usuario usuario);
    
}
