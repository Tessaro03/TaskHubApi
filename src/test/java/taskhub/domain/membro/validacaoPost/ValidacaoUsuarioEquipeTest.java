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

import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.membro.validacao.validacaoPost.ValidacaoUsuarioEquipe;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.tarefa.Tarefa;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoUsuarioEquipeTest {


    @InjectMocks
    private ValidacaoUsuarioEquipe validacao;
    
    @Mock
    private EquipeRepository repository;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private DadosCriacaoMembro dados;

    @Mock
    private Tarefa tarefa;

    @Mock
    private Projeto projeto;

    @Test
    @DisplayName("Usuario não faz parte da equipe")
    void testValidar1() {
        when(dados.idTarefa()).thenReturn(1l);
        when(dados.idTarefa()).thenReturn(1l);
        BDDMockito.given( tarefaRepository.getReferenceById(dados.idTarefa())).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);
        BDDMockito.given(repository.buscarUsuarioEmGrupo(dados.idUsuario(), tarefa.getProjeto().getId())).willReturn(false);
        Assertions.assertThrows(ValidacaoExcepetion.class,() -> validacao.validar(dados));
    }

    @Test
    @DisplayName("Usuario faz parte da equipe")
    void testValidar2() {
        when(dados.idTarefa()).thenReturn(1l);
        when(dados.idTarefa()).thenReturn(1l);
        BDDMockito.given( tarefaRepository.getReferenceById(dados.idTarefa())).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);
        BDDMockito.given(repository.buscarUsuarioEmGrupo(dados.idUsuario(), tarefa.getProjeto().getId())).willReturn(true);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dados));
    }
}
