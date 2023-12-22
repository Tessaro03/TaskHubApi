package taskhub.domain.usuario.validacao.validacaoPatch;

import org.springframework.stereotype.Service;

import taskhub.domain.usuario.DadosAlterarUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioSeAlterando implements ValidadorUsuarioPatch{

    @Override
    public void validar(DadosAlterarUsuario dados, Usuario usuario) {
        if (dados.id() != usuario.getId()) {
            throw new ValidacaoExcepetion("Usuário não pode alterar informações de outro usuario");
        }
    }
    
}
