package taskhub.domain.colaborador.validacaoPatch;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.colaborador.Colaborador;
import taskhub.domain.colaborador.DadosAlterarColaborador;
import taskhub.domain.colaborador.validacao.validacaoPatch.ValidacaoUsuarioAdministradorAlterarColaborador;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;


@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioAdministradorAlterarColaboradorTest {

    @InjectMocks
    private ValidacaoUsuarioAdministradorAlterarColaborador validacao;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock 
    private Usuario usuario;

    @Mock
    private Colaborador colaborador;

    @Mock
    private DadosAlterarColaborador dados;

    @Test
    @DisplayName("Usuario não admin definindo outro usuario como admin")
    void testValidar1() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1l)).willReturn(colaborador);
        BDDMockito.given(colaborador.getAdmin()).willReturn(false);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados, usuario));
    }

    @Test
    @DisplayName("Usuario  admin definindo outro usuario como admin")
    void testValidar2() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1l)).willReturn(colaborador);
        BDDMockito.given(colaborador.getAdmin()).willReturn(true);
        Assertions.assertDoesNotThrow( () -> validacao.validar(dados, usuario));
    }
}
