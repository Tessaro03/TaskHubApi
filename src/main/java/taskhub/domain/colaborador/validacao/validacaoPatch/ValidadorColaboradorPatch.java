package taskhub.domain.colaborador.validacao.validacaoPatch;

import taskhub.domain.colaborador.DadosAlterarColaborador;
import taskhub.domain.usuario.Usuario;

public interface ValidadorColaboradorPatch {
    
    void validar(DadosAlterarColaborador dados , Usuario usuario);
}
