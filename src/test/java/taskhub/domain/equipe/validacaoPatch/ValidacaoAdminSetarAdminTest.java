package taskhub.domain.equipe.validacaoPatch;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.equipe.DadosAlterarAdminEquipe;
import taskhub.domain.equipe.Equipe;
import taskhub.domain.equipe.validacao.validacaoPatch.ValidacaoAdminSetarAdmin;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoAdminSetarAdminTest {
    
    @InjectMocks
    private ValidacaoAdminSetarAdmin validacao;
    
    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DadosAlterarAdminEquipe dados;

    @Mock
    private Equipe equipe;

    @Mock
    private Usuario usuario;

    @Mock
    private Projeto projeto;

    @Mock
    private Equipe equipeAdmin;

    @Test
    @DisplayName("Usuario não admin definindo outro usuario como admin")
    void testValidar1(){
        when(usuario.getId()).thenReturn(1l);
        when(dados.idEquipe()).thenReturn(1l);

        BDDMockito.given(equipeRepository.getReferenceById(1l)).willReturn(equipe);
        BDDMockito.given(equipe.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1L);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 1l)).willReturn(equipeAdmin);
        BDDMockito.given(equipeAdmin.getAdmin()).willReturn(false);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados, usuario ));
    }

    @Test
    @DisplayName("Usuario admin definindo outro usuario como admin")
    void testValidar2(){
        when(usuario.getId()).thenReturn(1l);
        when(dados.idEquipe()).thenReturn(1l);

        BDDMockito.given(equipeRepository.getReferenceById(1l)).willReturn(equipe);
        BDDMockito.given(equipe.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1L);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 1l)).willReturn(equipeAdmin);
        BDDMockito.given(equipeAdmin.getAdmin()).willReturn(true);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados, usuario ));
    }

}
