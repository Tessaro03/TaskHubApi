package taskhub.controller;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.empresa.DadosAlterarEmpresa;
import taskhub.domain.empresa.DadosCadastroEmpresa;
import taskhub.domain.empresa.DadosListagemEmpresa;
import taskhub.domain.empresa.Empresa;
import taskhub.domain.empresa.EmpresaRepository;
import taskhub.domain.empresa.validacaoEmpresa.ValidadorEmpresa;
import taskhub.infra.service.BuscarUsuarioToken;

@RestController
@RequestMapping("/empresas")
@SecurityRequirement(name = "bearer-key")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repository;
    
    @Autowired 
    private ColaboradorRepository repositoryColaborador;

    @Autowired
    private ValidadorEmpresa validador;

    @Autowired
    private BuscarUsuarioToken usuarioToken;


    @GetMapping("/{id}")
    @Transactional
    @Operation(summary = "Buscar empresa por ID")
    public ResponseEntity buscarEmpresa(@PathVariable Long id){
        var empresa = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @GetMapping
    @Transactional
    @Operation(summary = "Listagem de empresas")
    public ResponseEntity buscarEmpresas(){
        var empresas = repository.findAll();
        return ResponseEntity.ok(empresas.stream().map(DadosListagemEmpresa::new));
    }

    @PostMapping
    @Operation(summary = "Cadastrar empresa")
    public void cadastrar(@RequestBody @Valid DadosCadastroEmpresa dados){
        var empresa = new Empresa(dados);
        repository.save(empresa);
    }

    @PatchMapping
    @Transactional
    @Operation(summary = "Alterar informações empresas")
    public ResponseEntity alterar(HttpServletRequest request, @RequestBody @Valid DadosAlterarEmpresa dados){
        validador.validarPatch(usuarioToken.usuarioToken(request));;
        var empresa = repository.getReferenceById(dados.id());
        empresa.atualizarInformacao(dados);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar empresa")
    public ResponseEntity deletar(HttpServletRequest request, @PathVariable Long id){
        validador.validarDelete( usuarioToken.usuarioToken(request));
        repositoryColaborador.deleteByEmpresaId(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
