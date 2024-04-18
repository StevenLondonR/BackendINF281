package Backend.BackendINF281.modulo_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Backend.BackendINF281.modulo_usuario.models.Voluntario;


public interface VoluntarioRepository extends JpaRepository<Voluntario,Integer>{

    Optional<Voluntario> findByIdvoluntario(Integer idvoluntario);
    

    @Modifying
    @Query("UPDATE Voluntario u SET u.rol = :rolvol WHERE u.idvoluntario = :idvol")
    void updateRolvol(@Param("idvol") Integer idvol, @Param("rolvol") String rolvol);

    

}
