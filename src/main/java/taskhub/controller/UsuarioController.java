package taskhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.usuario.DadosAlterarUsuario;
import taskhub.domain.usuario.DadosCadastroUsuario;
import taskhub.domain.usuario.DadosListagemUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.domain.usuario.UsuarioRepository;
import taskhub.domain.usuario.validacao.ValidadorUsuario;
import taskhub.infra.service.BuscarUsuarioToken;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired 
    private ValidadorUsuario validador;

    @Autowired
    private BuscarUsuarioToken usuarioToken;


    @GetMapping
    @Transactional
    public ResponseEntity listaUsuarios(){
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios.stream().map(DadosListagemUsuario::new)); 
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarUsuario(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário")
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
    }

    @PatchMapping
    @Transactional
    @Operation(summary = "Alterar usuário", description = "Alteração de nome e senha <br> Somente disponivel para o proprio usuário ")
    public ResponseEntity alterar(HttpServletRequest request, @RequestBody @Valid DadosAlterarUsuario dados){
        validador.validarPatch(dados,  usuarioToken.usuarioToken(request));
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacao(dados);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar Usuario", description = "Somente disponivel para o proprio usuário ")
    public ResponseEntity deletar(HttpServletRequest request, @PathVariable Long id){
        validador.validarDelete(id, usuarioToken.usuarioToken(request));
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    } 
}

