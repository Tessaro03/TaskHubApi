package taskhub.domain.colaborador.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoUsuarioAdministradorEmpresa implements ValidadorColaboradorDelete{

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    public void validar(Long id, Usuario usuario) {
        var usuarioColaborador = colaboradorRepository.getReferenceById(usuario.getId());

        if (usuario.getId() != id && !usuarioColaborador.getAdmin()) {
            throw new ValidacaoExcepetion("Usuário não tem permissão para remover outro usuário da empresa");        }
    }

    
}