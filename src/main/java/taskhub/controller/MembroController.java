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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.membro.DadosAlterarAdminMembro;
import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.membro.DadosListagemMembro;
import taskhub.domain.membro.Membro;
import taskhub.domain.membro.MembroRepository;
import taskhub.domain.membro.validacao.ValidadorMembro;
import taskhub.domain.tarefa.TarefaRepository;
import taskhub.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/membros")
public class MembroController {
    
    @Autowired
    private MembroRepository repository;
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorMembro> validador;

    @GetMapping
    @Transactional
    public ResponseEntity buscarMembros(){
        var membros = repository.findAll();
        return ResponseEntity.ok(membros.stream().map(DadosListagemMembro::new));
    }

    @PostMapping
    public void criar(@Valid @RequestBody DadosCriacaoMembro dados){
        validador.forEach(v -> v.validar(dados));
        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        var tarefa = tarefaRepository.getReferenceById(dados.idTarefa());
        var membro = new Membro(dados, usuario, tarefa);
        repository.save(membro);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity alterarAdmin(@Valid @RequestBody DadosAlterarAdminMembro dados){
        var membro = repository.getReferenceById(dados.idMembro());
        membro.alterarAdmin(dados);
        return ResponseEntity.ok(new DadosListagemMembro(membro));

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}