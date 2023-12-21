package taskhub.domain.tarefa.validacao.validacaoPatch;

import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.usuario.Usuario;

public interface ValidadorTarefaPatchUsuario {
    
    void validar(DadosAlterarTarefa dados, Usuario usuario);
}
