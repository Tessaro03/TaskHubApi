package taskhub.domain.projeto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskhub.domain.equipe.Equipe;
import taskhub.domain.tarefa.Tarefa;

@Table(name = "Projetos")
@Entity(name = "Projeto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Projeto {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFinal;

    private String status;
    private Prioridade prioridade;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Equipe> equipe = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tarefa> tarefas = new ArrayList<>();

    public Projeto(DadosCriacaoProjeto dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.status = "andamento"; 
        this.prioridade = dados.prioridade();
        if (dados.dataInicio() != null) {
            this.dataInicio = dados.dataInicio();
        }

        if (dados.dataFinal() != null) {
            this.dataFinal = dados.dataFinal();
        }
    }

    public void atualizarDados(DadosAlterarProjeto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.dataInicio() != null) {
            this.dataInicio = dados.dataInicio();
        }
        if (dados.dataFinal() != null) {
            this.dataFinal = dados.dataFinal();
        }
        if (dados.prioridade() != null) {
            this.prioridade = dados.prioridade();
        }

    }   
}
