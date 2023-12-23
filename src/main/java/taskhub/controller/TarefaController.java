package taskhub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.tarefa.DadosAlterarTarefa;
import taskhub.domain.tarefa.DadosCriacaoTarefa;
import taskhub.domain.tarefa.DadosListagemTarefa;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.tarefa.TarefaRepository;
import taskhub.domain.tarefa.validacao.ValidadorTarefa;
import taskhub.infra.service.BuscarUsuarioToken;
import taskhub.infra.service.DeleteEntidades;

@RestController
@RequestMapping("/tarefas")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "https://taskhubapi-production.up.railway.app")
public class TarefaController {
    
    @Autowired
    private TarefaRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private DeleteEntidades deleteEntidades;

    @Autowired
    private BuscarUsuarioToken usuarioToken;

    @Autowired
    private ValidadorTarefa validador;

    @GetMapping
    @Transactional
    @Operation(summary = "Buscar tarefas do usuario", description = "Todas tarefas em que o usuario faz parte.")
    public ResponseEntity buscarTarefas(HttpServletRequest request){
        var usuario = usuarioToken.usuarioToken(request);
        var tarefas = repository.buscarTarefasUsuario(usuario.getId());
        return ResponseEntity.ok(tarefas.stream().map(DadosListagemTarefa::new));
    }

    @GetMapping("/{id}")
    @Transactional
    @Operation(summary = "Buscar tarefa do usuario por id da Tarefa", description = "Apenas tarefa em que o usuario faz parte.")
    public ResponseEntity buscarTarefa(HttpServletRequest request, @PathVariable Long id){
        var usuario = usuarioToken.usuarioToken(request);
        var tarefa = repository.buscarTarefaIdUsuarioId( usuario.getId(), id);
        return ResponseEntity.ok(new DadosListagemTarefa(tarefa));
    }

    @PostMapping
    @Operation(summary = "Criação de Tarefa para projeto")
    public void criar(@Valid @RequestBody DadosCriacaoTarefa dados){
        validador.validarPost(dados);
        var projeto = projetoRepository.getReferenceById(dados.idProjeto());
        var tarefa = new Tarefa(dados, projeto);
        repository.save(tarefa);
    }

    @PatchMapping
    @Transactional
    @Operation(summary = "Alterar informações da tarefa", description = "Apenas usuario administrador do projeto ou tarefa.")
    public ResponseEntity alterar(HttpServletRequest request, @Valid @RequestBody DadosAlterarTarefa dados){
        validador.validarPatch(dados, usuarioToken.usuarioToken(request));
        var tarefa = repository.getReferenceById(dados.id());
        tarefa.atualizarDados(dados);
        return ResponseEntity.ok(new DadosListagemTarefa(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar tarefa", description = "Apenas usuario administrador do projeto ou tarefa.")
    public ResponseEntity deletar(HttpServletRequest request, @PathVariable Long id){
        validador.validadorDelete(id, usuarioToken.usuarioToken(request));
        deleteEntidades.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
