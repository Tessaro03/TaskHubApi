package taskhub.domain.colaborador.validacaoDelete;

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
import taskhub.domain.colaborador.validacao.validacaoDelete.ValidacaoUsuarioAdministradorEmpresa;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioAdministradorEmpresaTest {

    @InjectMocks
    private ValidacaoUsuarioAdministradorEmpresa validacao;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock 
    private Usuario usuario;

    @Mock
    private Colaborador colaborador;


    @Test
    @DisplayName("Usuario não admin deletando outro usuario")
    void testValidar1() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1l)).willReturn(colaborador);
        BDDMockito.given(colaborador.getAdmin()).willReturn(false);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(2l, usuario));
    }

    @Test
    @DisplayName("Usuario admin deletando outro usuario ")
    void testValidar2() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1l)).willReturn(colaborador);
        BDDMockito.given(colaborador.getAdmin()).willReturn(true);
        Assertions.assertDoesNotThrow( () -> validacao.validar(2l, usuario));
    }

    @Test
    @DisplayName("Usuario com o mesmo id ")
    void testValidar3() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1l)).willReturn(colaborador);
        Assertions.assertDoesNotThrow( () -> validacao.validar(1l, usuario));
    }

}
