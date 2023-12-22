package taskhub.domain.usuario.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.usuario.DadosAlterarUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.domain.usuario.validacao.validacaoDelete.ValidadorUsuarioDelete;
import taskhub.domain.usuario.validacao.validacaoPatch.ValidadorUsuarioPatch;

@Service
public class ValidadorUsuario {
    
    @Autowired
    private List<ValidadorUsuarioDelete> validadorDelete;

        
    @Autowired
    private List<ValidadorUsuarioPatch> validadorPatch;

        public void validarPatch(DadosAlterarUsuario dados, Usuario usuario){
        validadorPatch.forEach(v -> v.validar(dados, usuario));
    }

    public void validarDelete(Long id, Usuario usuario){
        validadorDelete.forEach(v -> v.validar(id, usuario));
    }

}
