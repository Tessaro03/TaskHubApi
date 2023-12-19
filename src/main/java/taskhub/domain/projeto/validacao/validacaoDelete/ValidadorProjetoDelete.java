package taskhub.domain.projeto.validacao.validacaoDelete;

import taskhub.domain.usuario.Usuario;

public interface ValidadorProjetoDelete {
    
        void validar(Usuario usuario, Long idProjeto);


}
