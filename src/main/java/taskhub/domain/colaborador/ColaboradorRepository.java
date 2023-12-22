package taskhub.domain.colaborador;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Colaborador c WHERE c.empresa.id = :idEmpresa")
    void deleteByEmpresaId(Long idEmpresa);

    @Modifying
    @Transactional
    @Query("DELETE FROM Colaborador c WHERE c.id = :id")
    void deleteByColaboradorId(Long id);
     
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Colaborador c WHERE c.usuario.id = :id")
    Boolean buscarUsuario(Long id);

    @Query("SELECT c FROM Colaborador c WHERE c.usuario.id = :id")
    Colaborador BuscarColaboradorIdUsuario(Long id);
}
