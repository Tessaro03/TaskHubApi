package taskhub.domain.projeto.validacaoPatch;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.projeto.validacao.validacaoPatch.ValidacaoDataProjetoAlteracao;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoDataProjetoAlteracaoTest {
    
    @InjectMocks
    private ValidacaoDataProjetoAlteracao validacao;

    @Mock
    private ProjetoRepository repository;

    @Mock
    private DadosAlterarProjeto dados;

    @Mock
    private Projeto projeto;

    @Test
    @DisplayName("Alterar projeto com data inicio após data final")
    void testValidar1(){

        //Arrange (Preparação)
        LocalDate dataFinal = LocalDate.of(2024, 1, 1);
        LocalDate dataInicio = LocalDate.of(2025, 1, 1);

        BDDMockito.given(repository.getReferenceById(dados.id())).willReturn(projeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinal);
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados));
    }

    @Test
    @DisplayName("Alterar projeto com data final após data inicio")
    void testValidar2(){

        //Arrange (Preparação)
        LocalDate dataFinal = LocalDate.of(2025, 1, 1);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        BDDMockito.given(repository.getReferenceById(dados.id())).willReturn(projeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinal);
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));
    }

     @Test
    @DisplayName("Alterar projeto com data final igual data inicio")
    void testValidar3(){

        //Arrange (Preparação)
        LocalDate dataFinal = LocalDate.of(2024, 1, 1);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        BDDMockito.given(repository.getReferenceById(dados.id())).willReturn(projeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinal);
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicio);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));
    }
}
