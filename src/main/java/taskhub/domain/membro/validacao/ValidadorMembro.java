package taskhub.domain.membro.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.membro.validacao.validacaoDelete.ValidadorMembroDelete;
import taskhub.domain.membro.validacao.validacaoPost.ValidadorMembroPost;
import taskhub.domain.membro.validacao.validacaoPost.ValidadorMembroPostUsuario;
import taskhub.domain.usuario.Usuario;

@Service
public class ValidadorMembro {
    

    @Autowired 
    private List<ValidadorMembroPost> validadorPost;

    @Autowired 
    private List<ValidadorMembroPostUsuario> validadorPostUsuario;

    
    @Autowired 
    private List<ValidadorMembroDelete> validadorDelete;

    public void validarPost(DadosCriacaoMembro dados, Usuario usuario){
        validadorPost.forEach(v -> v.validar(dados));
        validadorPostUsuario.forEach(v -> v.validar(usuario, dados));
    }

    public void validarDelete(Long id, Usuario usuario){

        validadorDelete.forEach(v -> v.validar(id, usuario));
    }
}
