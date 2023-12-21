package taskhub.domain.tarefa.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.domain.tarefa.validacao.validacaoDelete.ValidadorTarefaDelete;
import taskhub.domain.tarefa.validacao.validacaoPatch.ValidadorTarefaPatch;
import taskhub.domain.tarefa.validacao.validacaoPatch.ValidadorTarefaPatchUsuario;
import taskhub.domain.tarefa.validacao.validacaoPost.ValidadorTarefaPost;
import taskhub.domain.usuario.Usuario;

@Service
public class ValidadorTarefa {
    
    @Autowired
    private List<ValidadorTarefaPost> validadorPost;

    @Autowired
    private List<ValidadorTarefaPatchUsuario> validadorPatchUsuario;

        @Autowired
    private List<ValidadorTarefaPatch> validadorPatch;

    @Autowired
    private List<ValidadorTarefaDelete> validadorDelete;

    public void validarPost(DadosCriacaoTarefa dados){
        validadorPost.forEach(v -> v.validar(dados));
    }

     public void validarPatch(DadosAlterarTarefa dados, Usuario usuario){
        validadorPatchUsuario.forEach(v -> v.validar(dados, usuario));
        validadorPatch.forEach(v -> v.validar(dados));
    }

    public void validadorDelete(Long id, Usuario usuario){
        validadorDelete.forEach(v -> v.validar(id, usuario));
    }
}
