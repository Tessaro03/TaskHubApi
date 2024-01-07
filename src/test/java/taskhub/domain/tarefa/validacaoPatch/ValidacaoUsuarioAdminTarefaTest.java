package taskhub.domain.tarefa.validacaoPatch;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import taskhub.domain.equipe.Equipe;
import taskhub.domain.membro.Membro;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.tarefa.validacao.validacaoPatch.ValidacaoUsuarioAdminTarefa;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.MembroRepository;
import taskhub.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioAdminTarefaTest {
    

    @InjectMocks
    private ValidacaoUsuarioAdminTarefa validacao;

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private Usuario usuario;

    @Mock
    private DadosAlterarTarefa dados;

    @Mock
    private Tarefa tarefa;

    @Mock
    private Projeto projeto;

    @Mock
    private Equipe equipe;

    @Mock
    private Membro membro;


    @Test
    @DisplayName("Alteração de tarefa não sendo admin")
    void testValidar1(){

        when(dados.id()).thenReturn(1l);
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l, dados.id())).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 2l)).willReturn(equipe);

        BDDMockito.given(equipe.getAdmin()).willReturn(false);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(dados, usuario));
    }

    @Test
    @DisplayName("Alteração de tarefa sendo admin do Projeto")
    void testValidar2(){

        when(dados.id()).thenReturn(1l);
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l, dados.id())).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 2l)).willReturn(equipe);

        BDDMockito.given(equipe.getAdmin()).willReturn(true);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        Assertions.assertDoesNotThrow( () -> validacao.validar(dados, usuario));
    }

    @Test
    @DisplayName("Alteração de tarefa sendo admin da Tarefa")
    void testValidar3(){

      
        when(dados.id()).thenReturn(1l);
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l, dados.id())).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 2l)).willReturn(equipe);

        BDDMockito.given(membro.getAdmin()).willReturn(true);

        Assertions.assertDoesNotThrow( () -> validacao.validar(dados, usuario));
    }

    
}
