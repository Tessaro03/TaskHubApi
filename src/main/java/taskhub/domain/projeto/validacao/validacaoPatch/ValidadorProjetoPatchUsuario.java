package taskhub.domain.projeto.validacao.validacaoPatch;


import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.domain.usuario.Usuario;

public interface ValidadorProjetoPatchUsuario{
    
        void validar(Usuario usuario, DadosAlterarProjeto dados);

}
