package taskhub.domain.projeto.validacaoPatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.equipe.Equipe;
import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.domain.projeto.validacao.validacaoPatch.ValidacaoUsuarioAdministrador;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioAdministradorTest {

    @InjectMocks
    private ValidacaoUsuarioAdministrador validacao;

    @Mock
    private EquipeRepository equipeRepository;
    
    @Mock
    private Equipe equipe;

    @Mock 
    private Usuario usuario;

    @Mock 
    private DadosAlterarProjeto dados;

    @Test
    @DisplayName("Usuario não é Administrador para deletar projeto")
    void testValidar1(){

        //Arrange (Preparação)
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), dados.id())).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(false);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(usuario, dados));
    }

    @Test
    @DisplayName("Usuario é Administrador para deletar projeto")
    void testValidar2(){

        //Arrange (Preparação)
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), dados.id())).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(true);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validacao.validar(usuario, dados));
    }
}
