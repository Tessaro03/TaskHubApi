package taskhub.domain.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
import lombok.ToString;
import taskhub.domain.colaborador.Colaborador;
import taskhub.domain.equipe.Equipe;
import taskhub.domain.membro.Membro;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String login;
    private String email;
    private String senha;
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Equipe> equipes;
    
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Colaborador> colaboracao;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Membro> membro;

    public Usuario(@Valid DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = new BCryptPasswordEncoder().encode(dados.senha());
        this.login = dados.login();
        
    }
    
    public void atualizarInformacao(@Valid DadosAlterarUsuario dados) {
        
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
    }
    
  
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;

    }





}
