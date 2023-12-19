package taskhub.domain.projeto.validacao.validacaoPost;


import org.springframework.stereotype.Service;

import taskhub.domain.projeto.DadosCriacaoProjeto;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoDataFinalProjeto implements ValidadorProjetoPost{

    @Override
    public void validar(DadosCriacaoProjeto dados) {
        if ( dados.dataFinal().isBefore(dados.dataInicio())){
            throw new ValidacaoExcepetion("O Fim do Projeto não pode ser antes do inicio  ");
        }
    }
    
}
