package taskhub.domain.empresa.validacaoDelete;

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
import taskhub.domain.empresa.validacaoEmpresa.validacaoDelete.ValidacaoUsuarioAdminDeletarEmpresa;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;


@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioAdminDeletarEmpresaTest {

    
    @InjectMocks
    private ValidacaoUsuarioAdminDeletarEmpresa validacao;
    
    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private Colaborador colaborador;

    @Mock
    private Usuario usuario;

    @Test
    @DisplayName("Deletar empresa não sendo admin")
    void testValidar1() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1L)).willReturn(colaborador);
        BDDMockito.given(colaborador.getAdmin()).willReturn(false);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(usuario));

    }

    
    @Test
    @DisplayName("Deletar empresa sendo admin")
    void testValidar2() {
        when(usuario.getId()).thenReturn(1l);
        BDDMockito.given(colaboradorRepository.BuscarColaboradorIdUsuario(1L)).willReturn(colaborador);
        BDDMockito.given(colaborador.getAdmin()).willReturn(true);
        Assertions.assertDoesNotThrow( () -> validacao.validar(usuario));

    }
}
