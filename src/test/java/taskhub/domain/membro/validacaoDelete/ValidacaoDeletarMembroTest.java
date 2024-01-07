package taskhub.domain.membro.validacaoDelete;

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
import taskhub.domain.membro.validacao.validacaoDelete.ValidacaoDeletarMembro;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;
import taskhub.repository.MembroRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoDeletarMembroTest {

    @InjectMocks
    private ValidacaoDeletarMembro validacao;

    @Mock
    private MembroRepository membroRepository;

    @Mock 
    private EquipeRepository equipeRepository;

    @Mock
    private Equipe equipe;

    @Mock
    private Membro membro;

    @Mock
    private Membro membroDeletado;

    @Mock 
    private Usuario usuario;

    @Mock 
    private Usuario usuarioDeletado;

    @Mock
    private Tarefa tarefa;

    @Mock
    private Projeto projeto;

    @Test
    @DisplayName("Usuario deletando outro usuario não sendo admin")
    void testValidar1() {
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(membroRepository.getReferenceById(2l)).willReturn(membroDeletado);
        BDDMockito.given(membroDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(2l);

        BDDMockito.given(membroDeletado.getTarefa()).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given( membroRepository.buscarUsuarioEmGrupo(1l,1l)).willReturn(true);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(false);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(2l, usuario));
    }

    @Test
    @DisplayName("Usuario deletando outro usuario sendo admin do projeto")
    void testValidar2() {
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(membroRepository.getReferenceById(2l)).willReturn(membroDeletado);
        BDDMockito.given(membroDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(2l);

        BDDMockito.given(membroDeletado.getTarefa()).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given( membroRepository.buscarUsuarioEmGrupo(1l,1l)).willReturn(true);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(true);

        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(membro.getAdmin()).willReturn(false);

        Assertions.assertDoesNotThrow( () -> validacao.validar(2l, usuario));
    }

    
    @Test
    @DisplayName("Usuario deletando outro usuario sendo admin somento da tarefa")
    void testValidar3() {
        when(usuario.getId()).thenReturn(1l);
        
        BDDMockito.given(membroRepository.getReferenceById(2l)).willReturn(membroDeletado);
        BDDMockito.given(membroDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(2l);
        
        BDDMockito.given(membroDeletado.getTarefa()).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);
        BDDMockito.given(tarefa.getId()).willReturn(1l);
        
        BDDMockito.given(membroRepository.buscarUsuarioEmGrupo(1l,1l)).willReturn(true);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        
        BDDMockito.given(membroRepository.buscarMembroIdUsuarioIdTarefa(1l, 1l)).willReturn(membro);
        BDDMockito.given(membro.getAdmin()).willReturn(true);
        
        Assertions.assertDoesNotThrow( () -> validacao.validar(2l, usuario));
    }
    
    @Test
    @DisplayName("Usuario deletando si mesmo nao sendo admin")
    void testValidar4() {
        when(usuario.getId()).thenReturn(1l);

        BDDMockito.given(membroRepository.getReferenceById(2l)).willReturn(membroDeletado);
        BDDMockito.given(membroDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(1l);

        BDDMockito.given(membroDeletado.getTarefa()).willReturn(tarefa);
        BDDMockito.given(tarefa.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);
        BDDMockito.given(tarefa.getId()).willReturn(1l);

        BDDMockito.given( membroRepository.buscarUsuarioEmGrupo(1l,1l)).willReturn(true);
        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);

        Assertions.assertDoesNotThrow( () -> validacao.validar(2l, usuario));
    }
}
