package taskhub.domain.colaborador.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.colaborador.DadosCriacaoColaborador;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;

@Service
public class ValidacaoCadastroDuplicado implements ValidadorColaboradorPost{

    @Autowired
    private ColaboradorRepository repository;

    @Override
    public void validar(DadosCriacaoColaborador dados) {
        if (repository.buscarUsuario(dados.idUsuario())) {
            throw new ValidacaoExcepetion("Usuario já está em uma empresa");
        }
    }
}
