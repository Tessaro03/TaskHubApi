package taskhub.domain.membro;
    
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskhub.domain.tarefa.Tarefa;
import taskhub.domain.usuario.Usuario;

@Table(name = "membros")
@Entity(name = "Membro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id")
public class Membro {
 
    public Membro( DadosCriacaoMembro dados, Usuario usuario, Tarefa tarefa) {
        this.usuario = usuario;
        this.tarefa = tarefa;
        if (dados.admin() == true) {
            this.admin = dados.admin();
        } else {
            this.admin = false;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tarefa_id")
    private Tarefa tarefa;

    private Boolean admin;

    public void alterarAdmin(@Valid DadosAlterarAdminMembro dados) {
        this.admin = dados.admin();
    }

}
