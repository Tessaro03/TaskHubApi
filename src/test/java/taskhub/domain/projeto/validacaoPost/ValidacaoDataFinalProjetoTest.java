package taskhub.domain.projeto.validacaoPost;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskhub.domain.projeto.DadosCriacaoProjeto;
import taskhub.domain.projeto.validacao.validacaoPost.ValidacaoDataFinalProjeto;
import taskhub.infra.excepetion.ValidacaoExcepetion;

public class ValidacaoDataFinalProjetoTest {
    

    @Test
    @DisplayName("Criar projeto com data inicio após data final")
    void testValidar1(){ 

        //Arrange (Preparação)
        ValidacaoDataFinalProjeto validacao = new ValidacaoDataFinalProjeto();
        
        LocalDate dataFinal = LocalDate.of(2024, 1, 1);
        LocalDate dataInicio = LocalDate.of(2025, 1, 1);

        DadosCriacaoProjeto dados = mock(DadosCriacaoProjeto.class);
        when(dados.dataFinal()).thenReturn(dataFinal);
        when(dados.dataInicio()).thenReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados));

    }

    @Test
    @DisplayName("Criar projeto com data final após data inicio")
    void testValidar2(){ 

        //Arrange (Preparação)
        ValidacaoDataFinalProjeto validacao = new ValidacaoDataFinalProjeto();
        
        LocalDate dataFinal = LocalDate.of(2025, 1, 1);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        DadosCriacaoProjeto dados = mock(DadosCriacaoProjeto.class);
        when(dados.dataFinal()).thenReturn(dataFinal);
        when(dados.dataInicio()).thenReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));

    }

    @Test
    @DisplayName("Criar projeto com data final igual data inicio")
    void testValidar3(){ 

        //Arrange (Preparação)
        ValidacaoDataFinalProjeto validacao = new ValidacaoDataFinalProjeto();
        
        LocalDate dataFinal = LocalDate.of(2024, 1, 1);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        DadosCriacaoProjeto dados = mock(DadosCriacaoProjeto.class);
        when(dados.dataFinal()).thenReturn(dataFinal);
        when(dados.dataInicio()).thenReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));

    }

}
