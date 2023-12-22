package taskhub.domain.empresa.validacaoEmpresa.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioAdminDeletarEmpresa implements ValidadorEmpresaDelete{

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
