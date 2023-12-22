package taskhub.domain.colaborador.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.colaborador.DadosAlterarColaborador;
import taskhub.domain.colaborador.DadosCriacaoColaborador;
import taskhub.domain.colaborador.validacao.validacaoDelete.ValidadorColaboradorDelete;
import taskhub.domain.colaborador.validacao.validacaoPatch.ValidadorColaboradorPatch;
import taskhub.domain.colaborador.validacao.validacaoPost.ValidadorColaboradorPost;
import taskhub.domain.usuario.Usuario;

@Service
public class ValidadorColaborador {
    
    @Autowired
    private List<ValidadorColaboradorPost> validadorPost;

    @Autowired
    private List<ValidadorColaboradorDelete> validadorDelete;

    @Autowired
    private List<ValidadorColaboradorPatch> validadorPatch;

    public void validarPost(DadosCriacaoColaborador dados){
        validadorPost.forEach( v -> v.validar(dados));;
    }

    public void validarDelete(Long id, Usuario usuario) {
        validadorDelete.forEach(v -> v.validar(id, usuario));
    }

    public void validadorPatch(DadosAlterarColaborador dados, Usuario usuario){
        validadorPatch.forEach(v -> v.validar(dados, usuario));
    }

}
