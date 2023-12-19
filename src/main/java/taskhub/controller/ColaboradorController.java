package taskhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.colaborador.Colaborador;
import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.colaborador.DadosCriacaoColaborador;
import taskhub.domain.colaborador.DadosListagemColaborador;
import taskhub.domain.colaborador.validacao.ValidadorColaborador;
import taskhub.domain.empresa.EmpresaRepository;
import taskhub.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    
    @Autowired
    private List<ValidadorColaborador> validador;

    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Transactional
    public ResponseEntity listarColaborador(){
        var colaboradores = repository.findAll();
        return ResponseEntity.ok(colaboradores.stream().map(DadosListagemColaborador::new)); 
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCriacaoColaborador dados){
        
        validador.forEach( v -> v.validar(dados));
        
        var empresa =  empresaRepository.getReferenceById(dados.idEmpresa());
        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        var colaborador = new Colaborador(dados, usuario, empresa);
        repository.save(colaborador);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        repository.deleteByColaboradorId(id);
        return ResponseEntity.noContent().build();
    }
}

