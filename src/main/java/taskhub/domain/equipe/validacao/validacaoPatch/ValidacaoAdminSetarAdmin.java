package taskhub.domain.equipe.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskhub.domain.equipe.DadosAlterarAdminEquipe;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@Service
public class ValidacaoAdminSetarAdmin implements ValidadorEquipePatch{

   
    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void validar(DadosAlterarAdminEquipe dados, Usuario usuario) {

        var usuarioAlteracao = equipeRepository.getReferenceById(dados.idEquipe());
        var usuarioEquipe = equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), usuarioAlteracao.getProjeto().getId());
        if (!usuarioEquipe.getAdmin()) {
            throw new ValidacaoExcepetion("Usuario não possui permissão");
        }
    }
    
}
