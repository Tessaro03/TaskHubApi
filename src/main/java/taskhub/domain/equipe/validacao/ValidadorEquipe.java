package taskhub.domain.equipe.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.equipe.DadosAlterarAdminEquipe;
import taskhub.domain.equipe.validacao.validacaoDelete.ValidadorEquipeDelete;
import taskhub.domain.equipe.validacao.validacaoPatch.ValidadorEquipePatch;
import taskhub.domain.equipe.validacao.validacaoPost.ValidadorEquipePost;
import taskhub.domain.equipe.validacao.validacaoPost.ValidadorEquipePostUsuario;
import taskhub.domain.usuario.Usuario;


@Service
public class ValidadorEquipe {

        @Autowired
        private List<ValidadorEquipePost> validadorPost;

        @Autowired
        private List<ValidadorEquipePostUsuario> validadorPostUsuario;

        @Autowired
        private List<ValidadorEquipeDelete> validadorEquipeDelete;

        @Autowired
        private List<ValidadorEquipePatch> validadorEquipePatch;

        public void validarPost(DadosAdicionarUsuario dados, Usuario usuario){
                validadorPost.forEach(v -> v.validar(dados));
                validadorPostUsuario.forEach(v -> v.validar(dados, usuario));
        }

        public void validarDelete(Long id, Usuario usuario){
                validadorEquipeDelete.forEach(v -> v.validar(id, usuario));
        }

            public void validadorEquipePatch(DadosAlterarAdminEquipe dados, Usuario usuario){
                validadorEquipePatch.forEach(v -> v.validar(dados, usuario));
        }



}
