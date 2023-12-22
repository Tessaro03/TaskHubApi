package taskhub.domain.usuario.validacao.validacaoDelete;

import org.springframework.stereotype.Service;

import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioSeDeletando implements ValidadorUsuarioDelete{

    @Override
    public void validar(Long id, Usuario usuario) {
        if (id != usuario.getId()) {
            throw new ValidacaoExcepetion("Usuário não pode deletar outro Usuário");
        }
    }
    
}
