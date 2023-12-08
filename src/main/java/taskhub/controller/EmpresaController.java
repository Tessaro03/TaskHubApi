    package taskhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.empresa.DadosCadastroEmpresa;
import taskhub.domain.empresa.DadosListagemEmpresa;
import taskhub.domain.empresa.Empresa;
import taskhub.domain.empresa.EmpresaRepository;
import taskhub.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repository;
    
    @Autowired 
    private UsuarioRepository repositoryUsuario;

    @GetMapping
    @Transactional
    public ResponseEntity buscarEmpresa(){
        var empresa = repository.getReferenceById(1l);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroEmpresa dados){
        var usuario = repositoryUsuario.getReferenceById(dados.idUsuario());
        var empresa = new Empresa(dados, usuario);
        repository.save(empresa);
    }
}
