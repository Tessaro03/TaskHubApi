package taskhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskhub.domain.empresa.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
}
