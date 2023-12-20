package taskhub.domain.equipe.validacao.validacaoPatch;

import taskhub.domain.equipe.DadosAlterarAdminEquipe;
import taskhub.domain.usuario.Usuario;

public interface ValidadorEquipePatch {
    
    void validar(DadosAlterarAdminEquipe dados, Usuario usuario);
}
