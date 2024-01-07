package taskhub.domain.usuario.validacaoDelete;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskhub.domain.usuario.Usuario;
import taskhub.domain.usuario.validacao.validacaoDelete.ValidacaoUsuarioSeDeletando;
import taskhub.infra.excepetion.ValidacaoExcepetion;

public class ValidacaoUsuarioSeDeletandoTest {
    
    @Test
    @DisplayName("Usuario deletar outro Usuario")
    void testValidar1(){

        //Arrange (Preparação)
        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(1l);
        var idDeleta = 2l;

        ValidacaoUsuarioSeDeletando validacao = new ValidacaoUsuarioSeDeletando();

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class,() -> validacao.validar(idDeleta, usuario));
    }

    @Test
    @DisplayName("Usuario deletar si mesmo")
    void testValidar2(){

        //Arrange (Preparação)
        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(1l);
        var idDeleta = 1l;

        ValidacaoUsuarioSeDeletando validacao = new ValidacaoUsuarioSeDeletando();

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validacao.validar(idDeleta, usuario));
    }
}
