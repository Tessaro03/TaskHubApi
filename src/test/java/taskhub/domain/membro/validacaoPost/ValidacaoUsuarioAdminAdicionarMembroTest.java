package taskhub.domain.membro.validacaoPost;

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
import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.membro.Membro;
import taskhub.domain.membro.validacao.validacaoPost.ValidacaoUsuarioAdminAdicionarMembro;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.MembroRepository;
import taskhub.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioAdminAdicionarMembroTest {

    @InjectMocks
    private ValidacaoUsuarioAdminAdicionarMembro validacao;

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private DadosCriacaoMembro dados;

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
    @DisplayName("Adicionando membro não sendo admin de projeto e nem da tarefa")
    void testValidar1() {
        
        when(dados.idTarefa()).thenReturn(1l);
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById( dados.idTarefa())).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(usuario.getId(), dados.idTarefa())).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(usuario.getId(), dados.idTarefa())).willReturn(membro);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), tarefa.getProjeto().getId())).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(false);

        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(usuario, dados));
    }

    @Test
    @DisplayName("Adicionando membro não sendo admin de projeto e sendo de tarefa")
    void testValidar2() {
        
        when(dados.idTarefa()).thenReturn(1l);
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById( dados.idTarefa())).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(usuario.getId(), dados.idTarefa())).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(usuario.getId(), dados.idTarefa())).willReturn(membro);
        BDDMockito.given(membro.getAdmin()).willReturn(true);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), tarefa.getProjeto().getId())).willReturn(equipe);

        Assertions.assertDoesNotThrow( () -> validacao.validar(usuario, dados));
    }

    @Test
    @DisplayName("Adicionando membro sendo admin de projeto e não sendo de tarefa")
    void testValidar3() {
        
        when(dados.idTarefa()).thenReturn(1l);
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(tarefaRepository.getReferenceById( dados.idTarefa())).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);

        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(usuario.getId(), dados.idTarefa())).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(usuario.getId(), dados.idTarefa())).willReturn(membro);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(usuario.getId(), tarefa.getProjeto().getId())).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(true);

        Assertions.assertDoesNotThrow( () -> validacao.validar(usuario, dados));
    }
}
