package taskhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.empresa.DadosAlterarEmpresa;
import taskhub.domain.empresa.DadosCadastroEmpresa;
import taskhub.domain.empresa.DadosListagemEmpresa;
import taskhub.domain.empresa.Empresa;
import taskhub.domain.empresa.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repository;
    
    @Autowired 
    private ColaboradorRepository repositoryColaborador;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarEmpresa(@PathVariable Long id){
        var empresa = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @GetMapping
    @Transactional
    public ResponseEntity buscarEmpresas(){
        var empresas = repository.findAll();
        return ResponseEntity.ok(empresas.stream().map(DadosListagemEmpresa::new));
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroEmpresa dados){
        var empresa = new Empresa(dados);
        repository.save(empresa);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity alterar(@RequestBody @Valid DadosAlterarEmpresa dados){
        var empresa = repository.getReferenceById(dados.id());
        empresa.atualizarInformacao(dados);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        repositoryColaborador.deleteByEmpresaId(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
