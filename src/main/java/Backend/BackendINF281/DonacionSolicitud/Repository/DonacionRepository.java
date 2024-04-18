package Backend.BackendINF281.DonacionSolicitud.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Backend.BackendINF281.DonacionSolicitud.Models.Donacion;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;

public interface DonacionRepository extends JpaRepository<Donacion,Integer>{
    public Optional<Donacion> findByIddonacion(Integer id);

    @Modifying
    @Query("UPDATE Donacion d SET d.voluntario = :voluntario WHERE d.iddonacion = :iddon")
    void updateRolResponsable(@Param("iddon") Integer iddon, @Param("voluntario") Voluntario voluntario);


}


