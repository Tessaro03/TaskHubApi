package taskhub.domain.empresa.validacaoEmpresa.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;

@Service
public class ValidacaoUsuarioAdminEmpresaAlterar implements ValidadorEmpresaPatch{

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    public void validar(Usuario usuario) {
       var usuarioColaborador = colaboradorRepository.BuscarColaboradorIdUsuario(usuario.getId());
        if (!usuarioColaborador.getAdmin()) {
            throw new ValidacaoExcepetion("Usuario não possui permissão");
        }
    }
    
}
