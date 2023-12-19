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
import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.equipe.DadosAlterarAdminEquipe;
import taskhub.domain.equipe.DadosListagemEquipe;
import taskhub.domain.equipe.DadosListagemUsuarioEquipe;
import taskhub.domain.equipe.Equipe;
import taskhub.domain.equipe.EquipeRepository;
import taskhub.domain.equipe.validacao.ValidadorEquipe;
import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.usuario.UsuarioRepository;
import taskhub.infra.service.DeleteEntidades;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private List<ValidadorEquipe> validador;

    @Autowired
    private DeleteEntidades deleteEntidades;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarEquipe(@PathVariable Long id){
        var equipe = repository.equipeProjeto(id);
        return ResponseEntity.ok(new DadosListagemEquipe(equipe));
    }
    
    @PostMapping
    public void adicionarMembro(@RequestBody @Valid DadosAdicionarUsuario dados){
        validador.forEach( v -> v.validar(dados));
        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        var projeto = projetoRepository.getReferenceById(dados.idProjeto());
        var equipe = new Equipe(dados ,usuario, projeto);
        repository.save(equipe);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity alterarAdmin(@RequestBody @Valid DadosAlterarAdminEquipe dados){
        var equipe = repository.getReferenceById(dados.idEquipe());
        equipe.alterarAdmin(dados);
        return ResponseEntity.ok(new DadosListagemUsuarioEquipe(equipe));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        deleteEntidades.deletarEquipe(id);
        return ResponseEntity.noContent().build();
    }
}
