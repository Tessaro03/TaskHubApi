package taskhub.domain.tarefa.validacaoPost;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.domain.tarefa.validacao.validacaoPost.ValidacaoDataFinalTarefa;
import taskhub.infra.excepetion.ValidacaoExcepetion;

public class ValidacaoDataFinalTarefaTest {
    
    @Test
    @DisplayName("Criar tarefa com data inicio após data final da tarefa")
    void testValidar1(){

        //Arrange (Preparação)
        ValidacaoDataFinalTarefa validacao = new ValidacaoDataFinalTarefa();
        
        LocalDate dataFinal = LocalDate.of(2024, 1, 1);
        LocalDate dataInicio = LocalDate.of(2025, 1, 1);

        DadosCriacaoTarefa dados = mock(DadosCriacaoTarefa.class);
        when(dados.dataFinal()).thenReturn(dataFinal);
        when(dados.dataInicio()).thenReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados));

    }

    @Test
    @DisplayName("Criar tarefa com data final após data inicio da tarefa")
    void testValidar2(){

        //Arrange (Preparação)
        ValidacaoDataFinalTarefa validacao = new ValidacaoDataFinalTarefa();
        
        LocalDate dataFinal = LocalDate.of(2025, 1, 1);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        DadosCriacaoTarefa dados = mock(DadosCriacaoTarefa.class);
        when(dados.dataFinal()).thenReturn(dataFinal);
        when(dados.dataInicio()).thenReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow( () -> validacao.validar(dados));

    }
}
