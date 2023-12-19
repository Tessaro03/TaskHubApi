package taskhub.domain.colaborador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import taskhub.domain.empresa.Empresa;
import taskhub.domain.usuario.Usuario;

@Table(name = "colaboradores")
@Entity(name = "Colaborador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    
    private String cargo;
    
    private Boolean admin;
    
    public Colaborador(DadosCriacaoColaborador dados, Usuario usuario, Empresa empresa) {
        this.usuario = usuario;
        this.empresa = empresa;
        if (dados.admin() == true) {
            this.admin = true;
            
        } else {
            this.admin = false;
        }
        this.cargo = dados.cargo();
    }
}
