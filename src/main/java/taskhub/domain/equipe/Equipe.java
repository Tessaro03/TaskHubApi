package taskhub.domain.equipe;

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
import taskhub.domain.projeto.Projeto;
import taskhub.domain.usuario.Usuario;

@Table(name = "equipes")
@Entity(name = "Equipe")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id")
public class Equipe {
 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;
    
    private Boolean admin;
    
    public void alterarAdmin(@Valid DadosAlterarAdminEquipe dados) {
        this.admin = dados.admin();
    }
    
    public Equipe(DadosAdicionarUsuario dados, Usuario usuario, Projeto projeto) {
        this.projeto = projeto;
        this.usuario = usuario;
    
        this.admin = dados.admin();
        
    }
}
