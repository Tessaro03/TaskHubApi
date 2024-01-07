package taskhub.domain.equipe.validacaoPost;

import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.equipe.Equipe;
import taskhub.domain.equipe.validacao.validacaoPost.ValidacaoEquipeUsuarioAdmin;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoEquipeUsuarioAdminTest {
    
    @InjectMocks
    private ValidacaoEquipeUsuarioAdmin validacao;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private Equipe equipe;


    @Mock
    private DadosAdicionarUsuario dados;

    @Mock
    private Projeto projeto;

    @Mock
    private Usuario usuario;

    @Test
    @DisplayName("Usuario Não é Administrado")
    void testValidar1(){

        when(usuario.getId()).thenReturn(1l);
        when(dados.idProjeto()).thenReturn(1l);

        BDDMockito.given(equipeRepository.existeEquipePorIdProjeto(1l)).willReturn(true);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(false);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados, usuario));
    }

    @Test
    @DisplayName("Usuario é Administrado")
    void testValidar2(){

        when(usuario.getId()).thenReturn(1l);
        when(dados.idProjeto()).thenReturn(1l);

        BDDMockito.given(equipeRepository.existeEquipePorIdProjeto(1l)).willReturn(true);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(true);
        Assertions.assertDoesNotThrow( () -> validacao.validar(dados, usuario));
    }
    
}
