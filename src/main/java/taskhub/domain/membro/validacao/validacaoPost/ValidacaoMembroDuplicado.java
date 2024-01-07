package taskhub.domain.membro.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.MembroRepository;

@Service
public class ValidacaoMembroDuplicado implements ValidadorMembroPost {

    @Autowired
    private MembroRepository repository;

    @Override
    public void validar(DadosCriacaoMembro dados) {
       if (repository.buscarUsuarioEmGrupo(dados.idUsuario(), dados.idTarefa())) {
            throw new ValidacaoExcepetion("Usuario já faz parte da Tarefa");
        }
    }
    
}
