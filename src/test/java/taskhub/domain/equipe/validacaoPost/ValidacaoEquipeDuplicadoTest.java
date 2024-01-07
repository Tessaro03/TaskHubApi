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
import taskhub.domain.equipe.validacao.validacaoPost.ValidacaoEquipeDuplicado;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;


@ExtendWith(MockitoExtension.class)
public class ValidacaoEquipeDuplicadoTest {
    
    @InjectMocks
    private ValidacaoEquipeDuplicado validacao;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private Equipe equipe;

    @Mock
    private DadosAdicionarUsuario dados;

    @Test
    @DisplayName("Usuario adicionando usuario que ja é da equipe")
    void testValidar1(){
        when(dados.idProjeto()).thenReturn(1l);
        when(dados.idUsuario()).thenReturn(1l);
        BDDMockito.given(equipeRepository.buscarUsuarioEmGrupo(1l, 1l)).willReturn(true);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados));
    }

    @Test
    @DisplayName("Usuario adicionando usuario que não é da equipe")
    void testValidar2(){
        when(dados.idProjeto()).thenReturn(1l);
        when(dados.idUsuario()).thenReturn(1l);
        BDDMockito.given(equipeRepository.buscarUsuarioEmGrupo(1l, 1l)).willReturn(false);
        Assertions.assertDoesNotThrow( () -> validacao.validar(dados));
    }
}
