package taskhub.domain.tarefa.validacaoPost;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.projeto.Projeto;
import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.domain.tarefa.validacao.validacaoPost.ValidacaoExistenciaProjeto;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoExistenciaProjetoTest {
    
    @InjectMocks
    private ValidacaoExistenciaProjeto validacao;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private Projeto projeto;

    @Mock
    private DadosCriacaoTarefa dados;

    @Test
    @DisplayName("Criar tarefa em porjeto que existe")
    void testValidar1(){
        BDDMockito.given(projetoRepository.existsById(2L)).willReturn(true);
        when(dados.idProjeto()).thenReturn(2l);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));
    }

    @Test
    @DisplayName("Criar tarefa em porjeto que não existe")
    void testValidar2(){
        when(dados.idProjeto()).thenReturn(1l);
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados));
    }
}
