package taskhub.domain.tarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskhub.domain.membro.Membro;
import taskhub.domain.projeto.Prioridade;
import taskhub.domain.projeto.Projeto;

@Entity(name = "Tarefa")
@Table(name = "tarefas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Projeto projeto;

    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    
    private String status;
    private Prioridade prioridade;
    
    
    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Membro> membros = new ArrayList<>();
    
    public Tarefa(@Valid DadosCriacaoTarefa dados, Projeto projeto) {
        this.projeto = projeto;
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.dataInicio = dados.dataInicio();
        this.dataFinal = dados.dataFinal();
        this.status = "andamento";
        this.prioridade = dados.prioridade();
    }

    public void atualizarDados(DadosAlterarTarefa dados) {
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
        if (dados.status() != null) {
            this.status = dados.status();
        }
    }
}
