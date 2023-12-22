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
import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.equipe.DadosAlterarAdminEquipe;
import taskhub.domain.equipe.DadosListagemEquipe;
import taskhub.domain.equipe.DadosListagemUsuarioEquipe;
import taskhub.domain.equipe.Equipe;
import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.equipe.validacao.ValidadorEquipe;
import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.usuario.UsuarioRepository;
import taskhub.infra.service.BuscarUsuarioToken;
import taskhub.infra.service.DeleteEntidades;

@RestController
@RequestMapping("/equipes")
@SecurityRequirement(name = "bearer-key")
public class EquipeController {

    @Autowired
    private EquipeRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ValidadorEquipe validador;

    @Autowired
    private DeleteEntidades deleteEntidades;

    @Autowired
    private BuscarUsuarioToken usuarioToken;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarEquipe(@PathVariable Long id){
        var equipe = repository.equipeProjeto(id);
        return ResponseEntity.ok(new DadosListagemEquipe(equipe));
    }
    
    @PostMapping
    @Operation(summary = "Adicionar usuário a projeto")
    public void adicionarMembro(HttpServletRequest request, @RequestBody @Valid DadosAdicionarUsuario dados){
        var usuarioRequest = usuarioToken.usuarioToken(request);
        validador.validarPost(dados, usuarioRequest);
        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        var projeto = projetoRepository.getReferenceById(dados.idProjeto());
        var equipe = new Equipe(dados ,usuario, projeto);
        repository.save(equipe);
    }

    @PatchMapping
    @Transactional
    @Operation(summary = "Definir Administrador", description = "Definir como administrador ou remover cargo de administrador <br> Somente disponivel para Administrador <strong> (Projeto) <strong>")

    public ResponseEntity alterarAdmin(HttpServletRequest request,@RequestBody @Valid DadosAlterarAdminEquipe dados){

        validador.validadorEquipePatch(dados, usuarioToken.usuarioToken(request));
        var equipe = repository.getReferenceById(dados.idEquipe());
        equipe.alterarAdmin(dados);
        return ResponseEntity.ok(new DadosListagemUsuarioEquipe(equipe));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remover usuario da Equipe", description = "Somente disponivel para Administrador <strong> (Projeto) <strong>")
    public ResponseEntity deletar(HttpServletRequest request,@PathVariable Long id){
        var usuarioRequest = usuarioToken.usuarioToken(request);
        validador.validarDelete(id, usuarioRequest);
        deleteEntidades.deletarEquipe(id);
        return ResponseEntity.noContent().build();
    }
}
