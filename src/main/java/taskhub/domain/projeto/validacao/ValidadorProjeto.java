package taskhub.domain.projeto.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.domain.projeto.DadosCriacaoProjeto;
import taskhub.domain.projeto.validacao.validacaoDelete.ValidadorProjetoDelete;
import taskhub.domain.projeto.validacao.validacaoPatch.ValidadorProjetoPatch;
import taskhub.domain.projeto.validacao.validacaoPatch.ValidadorProjetoPatchUsuario;
import taskhub.domain.projeto.validacao.validacaoPost.ValidadorProjetoPost;
import taskhub.domain.usuario.Usuario;

@Service
public class ValidadorProjeto {
    
    @Autowired
    private List<ValidadorProjetoPost> validacaoPost;

    @Autowired
    private List<ValidadorProjetoPatch> validadorPatch;

    @Autowired
    private List<ValidadorProjetoPatchUsuario> validadorPatchUsuario;

    @Autowired
    private List<ValidadorProjetoDelete> validadorDelete;

    public void validarPost(DadosCriacaoProjeto dados){
        validacaoPost.forEach(v -> v.validar(dados));
    }

    public void validarPatch(Usuario usuario, DadosAlterarProjeto dados){
        validadorPatchUsuario.forEach(v -> v.validar(usuario, dados));
        validadorPatch.forEach(v -> v.validar(dados));
    }

    public void validarDelete(Usuario usuario, Long idProjeto){
        validadorDelete.forEach( v -> v.validar(usuario, idProjeto));
    }


}
