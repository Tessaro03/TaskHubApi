package taskhub.domain.equipe.validacaoDelete;

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
import taskhub.domain.equipe.validacao.validacaoDelete.ValidacaoDeletarOutroMembro;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.usuario.Usuario;
import taskhub.infra.excepetion.ValidacaoExcepetion;
import taskhub.repository.EquipeRepository;


@ExtendWith(MockitoExtension.class)
public class ValidacaoDeletarOutroMembroTest {
    
    @InjectMocks
    private ValidacaoDeletarOutroMembro validacao;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private Equipe equipe;

    @Mock
    private Projeto projeto;

    @Mock
    private Usuario usuario;

    @Mock
    private Usuario usuarioDeletado;

    @Mock
    private Equipe equipeUsuarioDeletado;

    @Test
    @DisplayName("Usuario Não é Administrado")
    void testValidar1(){

        when(usuario.getId()).thenReturn(1l);
        
        BDDMockito.given(equipeRepository.getReferenceById(2l)).willReturn(equipeUsuarioDeletado);
        BDDMockito.given(equipeUsuarioDeletado.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);


        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(false);


        BDDMockito.given(equipeUsuarioDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(2l);
        
        
        Assertions.assertThrows(ValidacaoExcepetion.class, () -> validacao.validar(2l, usuario));
    }


    @Test
    @DisplayName("Usuario é Administrado")
    void testValidar2(){
        when(usuario.getId()).thenReturn(1l);
        
        BDDMockito.given(equipeRepository.getReferenceById(2l)).willReturn(equipeUsuarioDeletado);
        BDDMockito.given(equipeUsuarioDeletado.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(1l,1l)).willReturn(equipe);
        BDDMockito.given(equipe.getAdmin()).willReturn(true);

        BDDMockito.given(equipeUsuarioDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(2l);
        
        Assertions.assertDoesNotThrow(() -> validacao.validar(2l, usuario));
    }

    @Test
    @DisplayName("Usuario esta se deletando")
    void testValidar3(){
        when(usuario.getId()).thenReturn(2l);
        
        BDDMockito.given(equipeRepository.getReferenceById(2l)).willReturn(equipeUsuarioDeletado);
        BDDMockito.given(equipeUsuarioDeletado.getProjeto()).willReturn(projeto);
        BDDMockito.given(projeto.getId()).willReturn(1l);

        BDDMockito.given(equipeRepository.buscarEquipeIdUsuarioIdProjeto(2l,1l)).willReturn(equipe);

        BDDMockito.given(equipeUsuarioDeletado.getUsuario()).willReturn(usuarioDeletado);
        BDDMockito.given(usuarioDeletado.getId()).willReturn(2l);
        
        Assertions.assertDoesNotThrow( () -> validacao.validar(2l, usuario));
    }
    
}
