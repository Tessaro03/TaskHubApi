package taskhub.domain.tarefa.validcaoDelete;

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
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.tarefa.validacao.validacaoDelete.ValidacaoAdminDeleteTarefa;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.MembroRepository;
import taskhub.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoAdminDeleteTarefaTest {
    

    @InjectMocks
    private ValidacaoAdminDeleteTarefa validacao;

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private Usuario usuario;

    @Mock
    private Tarefa tarefa;

    @Mock
    private Projeto projeto;

    @Mock
    private Equipe equipe;

    @Mock
    private Membro membro;


    @Test
    @DisplayName("Deletando tarefa não sendo admin")
    void testValidar1(){

        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l, 1l)).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 2l)).willReturn(equipe);

        BDDMockito.given(equipe.getAdmin()).willReturn(false);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(1l, usuario));
    }

    @Test
    @DisplayName("Deletando tarefa sendo admin do Projeto")
    void testValidar2(){

        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l, 1l)).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 2l)).willReturn(equipe);

        BDDMockito.given(equipe.getAdmin()).willReturn(true);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        Assertions.assertDoesNotThrow( () -> validacao.validar(1l, usuario));
    }

    @Test
    @DisplayName("Deletando tarefa sendo admin da Tarefa")
    void testValidar3(){

        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById(1L)).willReturn(tarefa);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(2l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l, 1l)).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l, 2l)).willReturn(equipe);

        BDDMockito.given(membro.getAdmin()).willReturn(true);

        Assertions.assertDoesNotThrow( () -> validacao.validar(1l, usuario));
    }

    
}
