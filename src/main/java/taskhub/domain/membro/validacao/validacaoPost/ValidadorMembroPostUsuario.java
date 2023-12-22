package taskhub.domain.membro.validacao.validacaoPost;

import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.usuario.Usuario;

public interface ValidadorMembroPostUsuario {
    

    void validar(Usuario usuario, DadosCriacaoMembro dados);
}
