package taskhub.domain.tarefa.validacao;

import org.springframework.stereotype.Service;

import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.infra.excepetion.ValidacaoExcepetion;


@Service
public class ValidacaoDataFinalTarefa implements ValidadorTarefa{

    @Override
    public void validar(DadosCriacaoTarefa dados) {

        if ( dados.dataFinal().isBefore(dados.dataInicio())){
            throw new ValidacaoExcepetion("O Fim da Tarefa não pode ser antes do inicio  ");
        } 

    }
    
}
