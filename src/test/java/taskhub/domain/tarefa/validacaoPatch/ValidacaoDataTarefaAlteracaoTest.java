package taskhub.domain.tarefa.validacaoPatch;

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
import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.tarefa.validacao.validacaoPatch.ValidacaoDataTarefaAlteracao;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.ProjetoRepository;
import taskhub.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoDataTarefaAlteracaoTest {
    

    @InjectMocks
    private ValidacaoDataTarefaAlteracao validacao;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private Tarefa tarefa;


    @Mock
    private Projeto projeto;

    @Mock
    private DadosAlterarTarefa dadosTarefa;

    @Test
    @DisplayName("Alterar tarefa com data inicio após data final da tarefa")
    void testValidar1(){
        // Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2024, 1, 1);
        LocalDate dataFinalProjeto = LocalDate.of(2028, 1, 1);      
          
        LocalDate dataInicioTarefa = LocalDate.of(2027, 1, 1);
        LocalDate dataFinalTarefa = LocalDate.of(2026, 1, 1);
    
        // Configurar stubbing para o método getReferenceById em tarefaRepository
        BDDMockito.given(projetoRepository.getReferenceById(2L)).willReturn(projeto);
        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        // Configurar stubbing para os métodos getDataInicio e getDataFinal em projeto
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinalProjeto);

        // Configurar stubbing para os métodos id, dataInicio e dataFinal em dadosTarefa
        when(dadosTarefa.id()).thenReturn(1L);
        when(dadosTarefa.dataInicio()).thenReturn(dataInicioTarefa);
        when(dadosTarefa.dataFinal()).thenReturn(dataFinalTarefa);
    
        // Act (Ação) e Assert (Assertiva)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dadosTarefa));
    }

    @Test
    @DisplayName("Alterar tarefa com data final após data final do projeto")
    void testValidar2(){
        // Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2024, 1, 1);
        LocalDate dataFinalProjeto = LocalDate.of(2028, 1, 1);      
          
        LocalDate dataFinalTarefa = LocalDate.of(2029, 1, 1);
    
        // Configurar stubbing para o método getReferenceById em tarefaRepository
        BDDMockito.given(projetoRepository.getReferenceById(2L)).willReturn(projeto);
        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        // Configurar stubbing para os métodos getDataInicio e getDataFinal em projeto
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinalProjeto);

        // Configurar stubbing para os métodos id, dataInicio e dataFinal em dadosTarefa
        when(dadosTarefa.id()).thenReturn(1L);
        when(dadosTarefa.dataFinal()).thenReturn(dataFinalTarefa);
    
        // Act (Ação) e Assert (Assertiva)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dadosTarefa));
    }

    @Test
    @DisplayName("Alterar tarefa com data inicial antes da data inicial do projeto")
    void testValidar3(){
        // Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2024, 1, 1);
        LocalDate dataFinalProjeto = LocalDate.of(2028, 1, 1);      
          
        LocalDate dataInicioTarefa = LocalDate.of(2023, 1, 1);
    
        // Configurar stubbing para o método getReferenceById em tarefaRepository
        BDDMockito.given(projetoRepository.getReferenceById(2L)).willReturn(projeto);
        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        // Configurar stubbing para os métodos getDataInicio e getDataFinal em projeto
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinalProjeto);

        // Configurar stubbing para os métodos id, dataInicio e dataFinal em dadosTarefa
        when(dadosTarefa.id()).thenReturn(1L);
        when(dadosTarefa.dataInicio()).thenReturn(dataInicioTarefa);
    
        // Act (Ação) e Assert (Assertiva)
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dadosTarefa));
    }

    @Test
    @DisplayName("Datas estão ok")
    void testValidar4(){
       // Arrange (Preparação)
        LocalDate dataInicioProjeto = LocalDate.of(2024, 1, 1);
        LocalDate dataFinalProjeto = LocalDate.of(2028, 1, 1);      
          
        LocalDate dataInicioTarefa = LocalDate.of(2025, 1, 1);
        LocalDate dataFinalTarefa = LocalDate.of(2027, 1, 1);
    
        // Configurar stubbing para o método getReferenceById em tarefaRepository
        BDDMockito.given(projetoRepository.getReferenceById(2L)).willReturn(projeto);
        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        // Configurar stubbing para os métodos getDataInicio e getDataFinal em projeto
        BDDMockito.given(projeto.getDataInicio()).willReturn(dataInicioProjeto);
        BDDMockito.given(projeto.getDataFinal()).willReturn(dataFinalProjeto);

        // Configurar stubbing para os métodos id, dataInicio e dataFinal em dadosTarefa
        when(dadosTarefa.id()).thenReturn(1L);
        when(dadosTarefa.dataInicio()).thenReturn(dataInicioTarefa);
        when(dadosTarefa.dataFinal()).thenReturn(dataFinalTarefa);
    
        // Act (Ação) e Assert (Assertiva)
        Assertions.assertDoesNotThrow(() -> validacao.validar(dadosTarefa));
    }


}
