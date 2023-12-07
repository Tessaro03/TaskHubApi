package taskhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.usuario.DadosCadastroUsuario;
import taskhub.domain.usuario.DadosListagemUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    
    @GetMapping
    @Transactional
    public ResponseEntity buscarUsuario(){
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios.stream().map(DadosListagemUsuario::new)); 
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
    }
}

