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
import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.domain.tarefa.DadosListagemTarefa;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.tarefa.TarefaRepository;
import taskhub.domain.tarefa.validacao.ValidadorTarefa;
import taskhub.infra.service.DeleteEntidades;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private DeleteEntidades deleteEntidades;

    @Autowired
    private List<ValidadorTarefa> validador;

    @GetMapping
    @Transactional
    public ResponseEntity buscarTarefas(){
        var tarefas = repository.findAll();
        return ResponseEntity.ok(tarefas.stream().map(DadosListagemTarefa::new));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarTarefa(@PathVariable Long id){
        var tarefa = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTarefa(tarefa));
    }

    @PostMapping
    public void criar(@Valid @RequestBody DadosCriacaoTarefa dados){
        validador.forEach(v -> v.validar(dados));
        var projeto = projetoRepository.getReferenceById(dados.idProjeto());
        var tarefa = new Tarefa(dados, projeto);
        repository.save(tarefa);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity alterar(@Valid @RequestBody DadosAlterarTarefa dados){
        var tarefa = repository.getReferenceById(dados.id());
        tarefa.atualizarDados(dados);
        return ResponseEntity.ok(new DadosListagemTarefa(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        deleteEntidades.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
