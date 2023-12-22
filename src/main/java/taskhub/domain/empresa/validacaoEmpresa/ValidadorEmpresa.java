package taskhub.domain.empresa.validacaoEmpresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.empresa.validacaoEmpresa.validacaoDelete.ValidadorEmpresaDelete;
import taskhub.domain.empresa.validacaoEmpresa.validacaoPatch.ValidadorEmpresaPatch;
import taskhub.domain.usuario.Usuario;

@Service
public class ValidadorEmpresa {
    
    @Autowired
    private List<ValidadorEmpresaDelete> validadorDelete;

    @Autowired
    private List<ValidadorEmpresaPatch> validadorPatch;

    public void validarDelete(Usuario usuario){
        validadorDelete.forEach( v -> v.validar(usuario));
    }

     public void validarPatch(Usuario usuario){
        validadorPatch.forEach( v -> v.validar(usuario));
    }
}
