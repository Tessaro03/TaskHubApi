package taskhub.domain.projeto.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ProjetoRepository;

@Service
public class ValidacaoDataProjetoAlteracao implements ValidadorProjetoPatch{

    @Autowired
    private ProjetoRepository repository;

    @Override
    public void validar(DadosAlterarProjeto dados) {
        
        var projeto = repository.getReferenceById(dados.id());
        var dataFinal = projeto.getDataFinal();
        var dataInicio = projeto.getDataInicio();

        if (dados.dataFinal() != null) {
            dataFinal = dados.dataFinal();    
        }
        if (dados.dataInicio() != null) {
            dataInicio = dados.dataInicio();
        }

        if ( dataFinal.isBefore(dataInicio)){
            throw new ValidacaoExcepetion("O Fim do Projeto não pode ser antes do inicio  ");
        }
    }
    
}
