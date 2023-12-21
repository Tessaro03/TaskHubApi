package taskhub.domain.tarefa.validacao.validacaoPatch;

import taskhub.domain.tarefa.DadosAlterarTarefa;

public interface ValidadorTarefaPatch {
    
    void validar(DadosAlterarTarefa dados);
}
