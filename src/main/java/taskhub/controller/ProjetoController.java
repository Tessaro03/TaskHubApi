package taskhub.controller;

import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.equipe.DadosAdicionarUsuario;
import taskhub.domain.projeto.DadosAlterarProjeto;
import taskhub.domain.projeto.DadosCriacaoProjeto;
import taskhub.domain.projeto.DadosListagemProjeto;
import taskhub.domain.projeto.Projeto;
import taskhub.domain.projeto.ProjetoRepository;
import taskhub.domain.projeto.validacao.ValidadorProjeto;
import taskhub.infra.service.BuscarUsuarioToken;
import taskhub.infra.service.DeleteEntidades;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private DeleteEntidades deleteEntidades;

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private EquipeController equipeController;

    @Autowired
    private ValidadorProjeto validador;


    @Autowired
    private BuscarUsuarioToken usuarioToken;

    @GetMapping
    @Transactional
    public ResponseEntity buscarProjetos(HttpServletRequest request){
        var usuario = usuarioToken.usuarioToken(request);
        var projetos = repository.buscarProjetosUsario(usuario.getId());
        return ResponseEntity.ok(projetos.stream().map(DadosListagemProjeto::new)); 
    }
    
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarProjeto(HttpServletRequest request, @PathVariable Long id){
        var usuario = usuarioToken.usuarioToken(request);
        var projeto = repository.buscarProjetoUsario(usuario.getId(),id);
        return ResponseEntity.ok(new DadosListagemProjeto(projeto));
    }

    @PostMapping
    public void criar(HttpServletRequest request,@RequestBody @Valid DadosCriacaoProjeto dados){
        var usuario = usuarioToken.usuarioToken(request);
        validador.validarPost(dados);
        var projeto = new Projeto(dados);
        repository.save(projeto);
        equipeController.adicionarMembro(request, new DadosAdicionarUsuario(projeto.getId(), usuario.getId() , true));
    }

    @PatchMapping
    @Transactional
    public ResponseEntity alterar(HttpServletRequest request, @RequestBody @Valid DadosAlterarProjeto dados){
        validador.validarPatch( usuarioToken.usuarioToken(request), dados);
        var projeto = repository.getReferenceById(dados.id());
        projeto.atualizarDados(dados);
        return ResponseEntity.ok(new DadosListagemProjeto(projeto));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(HttpServletRequest request, @PathVariable Long id){
        validador.validarDelete(usuarioToken.usuarioToken(request), id);
        deleteEntidades.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }
}
