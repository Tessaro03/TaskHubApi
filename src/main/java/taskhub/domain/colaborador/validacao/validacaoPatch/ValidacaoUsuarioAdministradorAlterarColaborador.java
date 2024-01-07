package taskhub.domain.colaborador.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.colaborador.DadosAlterarColaborador;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;

@Service
public class ValidacaoUsuarioAdministradorAlterarColaborador implements ValidadorColaboradorPatch{

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    public void validar(DadosAlterarColaborador dados, Usuario usuario) {
        var usuarioColaborador = colaboradorRepository.BuscarColaboradorIdUsuario(usuario.getId());  
        if (!usuarioColaborador.getAdmin()) {
            throw new ValidacaoExcepetion("Usuário não possui permissão para alterar dados");
        }
    }
    


}
