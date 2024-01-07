package taskhub.domain.membro.validacaoPost;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.membro.validacao.validacaoPost.ValidacaoMembroDuplicado;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.MembroRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoMembroDuplicadoTest {


    @InjectMocks
    private ValidacaoMembroDuplicado validacao;

    @Mock
    private MembroRepository repository;

    @Mock
    private DadosCriacaoMembro dados;


    @Test
    @DisplayName("Usuario já faz parte da equipe")
    void testValidar1() {
        BDDMockito.given(repository.buscarUsuarioEmGrupo(dados.idUsuario(), dados.idTarefa())).willReturn(true);
        Assertions.assertThrows(ValidacaoExcepetion.class,() -> validacao.validar(dados));
    }

    @Test
    @DisplayName("Usuario não faz parte da equipe")
    void testValidar2() {
        BDDMockito.given(repository.buscarUsuarioEmGrupo(dados.idUsuario(), dados.idTarefa())).willReturn(false);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));
    }
}
