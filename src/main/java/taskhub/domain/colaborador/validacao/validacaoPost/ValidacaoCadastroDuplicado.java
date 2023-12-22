package taskhub.domain.colaborador.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.colaborador.DadosCriacaoColaborador;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoCadastroDuplicado implements ValidadorColaboradorPost{

    @Autowired
    private ColaboradorRepository repository;

    @Override
    public void validar(DadosCriacaoColaborador dados) {
        if (repository.buscarUsuario(dados.idUsuario()) != null) {
            throw new ValidacaoExcepetion("Usuario já está em uma empresa");
        }
    }
}
