package taskhub.domain.empresa;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskhub.domain.colaborador.Colaborador;

@Table(name = "empresas")
@Entity(name = "Empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @OneToMany( mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradores;
    
    public Empresa(DadosCadastroEmpresa dados) {
        this.nome = dados.nome();
    }
    
    public void atualizarInformacao(@Valid DadosAlterarEmpresa dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
       
    }
    
}
