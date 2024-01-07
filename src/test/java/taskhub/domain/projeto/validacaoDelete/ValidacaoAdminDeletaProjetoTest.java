package taskhub.domain.projeto.validacaoDelete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.equipe.Equipe;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.projeto.validacao.validacaoDelete.ValidacaoAdminDeletaProjeto;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoAdminDeletaProjetoTest {
    
    @InjectMocks
    private ValidacaoAdminDeletaProjeto validacao;

    @Mock
    private Equipe equipe;

    @Mock
    private Projeto projeto;

    @Mock 
    private Usuario usuario;

    @Mock
    private EquipeRepository equipeRepository;


    @Test
    @DisplayName("Usuario deletar projeto não sendo administrador")
    void testValidar1(){

        //Arrange (Preparação)
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), projeto.getId())).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(false);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(usuario, projeto.getId() ));
    }

    
    @Test
    @DisplayName("Usuario deletar projeto sendo administrador")
    void testValidar2(){

        //Arrange (Preparação)
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), projeto.getId())).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(true);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow( () -> validacao.validar(usuario, projeto.getId() ));
    }
}
