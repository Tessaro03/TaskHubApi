package taskhub.domain.tarefa.validacao.validacaoDelete;

import taskhub.domain.usuario.Usuario;

public interface ValidadorTarefaDelete {

    void validar(Long idTarefa, Usuario usuario);


}
