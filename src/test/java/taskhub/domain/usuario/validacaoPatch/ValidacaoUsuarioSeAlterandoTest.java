package taskhub.domain.usuario.validacaoPatch;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskhub.domain.usuario.DadosAlterarUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.domain.usuario.validacao.validacaoPatch.ValidacaoUsuarioSeAlterando;
import taskhub.infra.excepetion.ValidacaoExcepetion;

public class ValidacaoUsuarioSeAlterandoTest {
    
    
    @Test
    @DisplayName("Usuario alterar dados de outro usuario")
    void testValidar1() {

        //Arrange (Preparação)
        ValidacaoUsuarioSeAlterando validador = new ValidacaoUsuarioSeAlterando();

        DadosAlterarUsuario dados = mock(DadosAlterarUsuario.class);
        when(dados.id()).thenReturn(1L);

        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(3L);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validador.validar(dados, usuario));
    }

     @Test
    @DisplayName("Usuario alterar dados de si mesmo")
    void testValidar2() {

        //Arrange (Preparação)
        ValidacaoUsuarioSeAlterando validador = new ValidacaoUsuarioSeAlterando();

        DadosAlterarUsuario dados = mock(DadosAlterarUsuario.class);
        when(dados.id()).thenReturn(1L);

        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(1L);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validador.validar(dados, usuario));
    }
}
