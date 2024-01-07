package taskhub.domain.tarefa.validacaoPost;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
import taskhub.domain.tarefa.validacao.validacaoPost.ValidacaoDataTarefaComProjeto;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoDataTarefaComProjetoTest {


    @InjectMocks
    private ValidacaoDataTarefaComProjeto validacao;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private Projeto projeto;

    @Mock
    private DadosCriacaoTarefa dadosTarefa;

    @Test
    @DisplayName("Data inicial da tarefa antes da data inicial do Projeto")
    void testValidar1(){

        //Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2025, 1, 1);
        LocalDate dataInicioTarefa = LocalDate.of(2024, 1, 1);

        BDDMockito.given(projetoRepository.getReferenceById(projeto.getId())).willReturn(projeto);
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);

        when(dadosTarefa.dataInicio()).thenReturn(dataInicioTarefa);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dadosTarefa));
    }
    
    @Test
    @DisplayName("Data Inicial da tarefa depois da data final do Projeto")
    void testValidar2(){

        //Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2024, 1, 1);
        LocalDate dataFinalProjeto = LocalDate.of(2027, 1, 1);
        
        LocalDate dataInicioTarefa = LocalDate.of(2029, 1, 1);

        BDDMockito.given(projetoRepository.getReferenceById(projeto.getId())).willReturn(projeto);
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinalProjeto);

        when(dadosTarefa.dataInicio()).thenReturn(dataInicioTarefa);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dadosTarefa));
    }

    @Test
    @DisplayName("Data Final da tarefa depois da data final do Projeto")
    void testValidar3(){

        //Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2024, 1, 1);
        LocalDate dataFinalProjeto = LocalDate.of(2027, 1, 1);
        
        LocalDate dataInicioTarefa = LocalDate.of(2025, 1, 1);
        LocalDate dataFinalTarefa = LocalDate.of(2028, 1, 1);

        BDDMockito.given(projetoRepository.getReferenceById(projeto.getId())).willReturn(projeto);
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinalProjeto);

        when(dadosTarefa.dataInicio()).thenReturn(dataInicioTarefa);
        when(dadosTarefa.dataFinal()).thenReturn(dataFinalTarefa);

        //Assert (Assertiva) e Act (Ação)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dadosTarefa));
    }
}
