package taskhub.controller;


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
import taskhub.domain.membro.DadosAlterarAdminMembro;
import taskhub.domain.membro.DadosCriacaoMembro;
import taskhub.domain.membro.DadosListagemMembro;
import taskhub.domain.membro.Membro;
import taskhub.domain.membro.MembroRepository;
import taskhub.domain.membro.validacao.ValidadorMembro;
import taskhub.domain.tarefa.TarefaRepository;
import taskhub.domain.usuario.UsuarioRepository;
import taskhub.infra.service.BuscarUsuarioToken;

@RestController
@RequestMapping("/membros")
@SecurityRequirement(name = "bearer-key")
public class MembroController {
    
    @Autowired
    private MembroRepository repository;
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidadorMembro validador;

    @Autowired
    private BuscarUsuarioToken usuarioToken;

    @GetMapping
    @Transactional
    public ResponseEntity buscarMembros(){
        var membros = repository.findAll();
        return ResponseEntity.ok(membros.stream().map(DadosListagemMembro::new));
    }

    @PostMapping
    @Operation(summary = "Designar Tarefa para Usuário", description = "Disponivel somente para usuário administrador <strong> (Projeto e Tarefa) </strong>.")
    public void criar(HttpServletRequest request, @Valid @RequestBody DadosCriacaoMembro dados){
        validador.validarPost(dados , usuarioToken.usuarioToken(request));
        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        var tarefa = tarefaRepository.getReferenceById(dados.idTarefa());
        var membro = new Membro(dados, usuario, tarefa);
        repository.save(membro);
    }

    @PatchMapping
    @Transactional
    @Operation(summary = "Definir administrador tarefa", description = "Disponivel somente para usuário administrador <strong> (Projeto e Tarefa) </strong>.")
    public ResponseEntity alterarAdmin(@Valid @RequestBody DadosAlterarAdminMembro dados){
        var membro = repository.getReferenceById(dados.idMembro());
        membro.alterarAdmin(dados);
        return ResponseEntity.ok(new DadosListagemMembro(membro));

    }


    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar usuario tarefa", description = "Disponivel somente para usuário administrador <strong> (Projeto e Tarefa) </strong> e o próprio Usuário.")
    public ResponseEntity deletar(HttpServletRequest request, @PathVariable Long id){
        validador.validarDelete(id,usuarioToken.usuarioToken(request));
        repository.deletarMembro(id);
        return ResponseEntity.noContent().build();
    }
}
