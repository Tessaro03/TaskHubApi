package taskhub.controller;

import java.util.List;

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
import taskhub.domain.colaborador.Colaborador;
import taskhub.domain.colaborador.ColaboradorRepository;
import taskhub.domain.colaborador.DadosAlterarColaborador;
import taskhub.domain.colaborador.DadosCriacaoColaborador;
import taskhub.domain.colaborador.DadosListagemColaborador;
import taskhub.domain.colaborador.validacao.ValidadorColaborador;
import taskhub.domain.empresa.EmpresaRepository;
import taskhub.domain.usuario.UsuarioRepository;
import taskhub.infra.service.BuscarUsuarioToken;

@RestController
@RequestMapping("/colaboradores")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "https://taskhubapi-production.up.railway.app")
public class ColaboradorController {
    
    @Autowired
    private ValidadorColaborador validador;

    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioToken usuarioToken;

    @GetMapping
    @Transactional
    public ResponseEntity listarColaborador(){
        var colaboradores = repository.findAll();
        return ResponseEntity.ok(colaboradores.stream().map(DadosListagemColaborador::new)); 
    }

    @PostMapping
    @Operation(summary = "Associar usuário a empresa")
    public void cadastrar( @RequestBody @Valid DadosCriacaoColaborador dados){
        validador.validarPost(dados);
        var empresa =  empresaRepository.getReferenceById(dados.idEmpresa());
        var usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        var colaborador = new Colaborador(dados, usuario, empresa);
        repository.save(colaborador);
    }

    @PatchMapping
    @Transactional
    @Operation(summary = "Alterar informações colaborador empresa", description = "Apenas administrador da empresa pode definir cargo e status de administrador")
    public ResponseEntity alterar(HttpServletRequest request, @PathVariable DadosAlterarColaborador dados){
        validador.validadorPatch(dados, usuarioToken.usuarioToken(request));
        var usuario = repository.getReferenceById(dados.idColaborador());
        usuario.setAdmin(dados.admin());
        usuario.setCargo(dados.cargo());
        repository.save(usuario);
        return ResponseEntity.ok(new DadosListagemColaborador(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remover usuário da empresa", description = "Apenas o próprio usuário pode se remover ou administrador empresa")
    public ResponseEntity deletar(HttpServletRequest request,@PathVariable Long id){
        validador.validarDelete(id, usuarioToken.usuarioToken(request));
        repository.deleteByColaboradorId(id);
        return ResponseEntity.noContent().build();
    }
}

