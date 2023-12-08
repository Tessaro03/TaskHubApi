package taskhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.empresa.EmpresaRepository;
import taskhub.domain.usuario.DadosAlterarUsuario;
import taskhub.domain.usuario.DadosCadastroUsuario;
import taskhub.domain.usuario.DadosListagemUsuario;
import taskhub.domain.usuario.Usuario;
import taskhub.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;
    
    @GetMapping
    @Transactional
    public ResponseEntity listaUsuarios(){
        List<Usuario> usuarios = repository.listaUsuariosAtivos();
        return ResponseEntity.ok(usuarios.stream().map(DadosListagemUsuario::new)); 
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarUsuario(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterar(@RequestBody @Valid DadosAlterarUsuario dados){
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacao(dados);
        if (dados.idEmpresa() != null){
            var empresa = empresaRepository.getReferenceById(dados.idEmpresa());
            usuario.atualizarEmpresa(empresa);
        }
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.desatiivarConta();
        return ResponseEntity.noContent().build();
    }
}

