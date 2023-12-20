package taskhub.domain.equipe.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.equipe.EquipeRepository;
import taskhub.infra.excepetion.ValidacaoExcepetion;

@Service
public class ValidacaoEquipeDuplicado implements ValidadorEquipePost{

    @Autowired
    private EquipeRepository repository;

    @Override
    public void validar(DadosAdicionarUsuario dados) {
        if (repository.buscarUsuarioEmGrupo(dados.idUsuario(), dados.idProjeto())) {
            throw new ValidacaoExcepetion("Usuario já faz parte do grupo");
        }
    }
    
}
