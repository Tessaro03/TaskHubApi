package taskhub.domain.colaborador.validacaoPost;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.colaborador.DadosCriacaoColaborador;
import taskhub.domain.colaborador.validacao.validacaoPost.ValidacaoCadastroDuplicado;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ColaboradorRepository;


@ExtendWith(MockitoExtension.class)
public class ValidacaoCadastroDuplicadoTest {

    @InjectMocks
    private ValidacaoCadastroDuplicado validacao;


    @Mock
    private ColaboradorRepository repository;

    @Mock
    private DadosCriacaoColaborador dados;

    @Test
    @DisplayName("Usuario já faz parte de uma empresa")
    void testValidar1() {
        when(dados.idUsuario()).thenReturn(1l);
        BDDMockito.given(repository.buscarUsuario(1l)).willReturn(true);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados));
    }

    @Test
    @DisplayName("Usuario não faz parte de uma empresa")
    void testValidar2() {
        when(dados.idUsuario()).thenReturn(1l);
        BDDMockito.given(repository.buscarUsuario(1l)).willReturn(false);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));
    }
}
