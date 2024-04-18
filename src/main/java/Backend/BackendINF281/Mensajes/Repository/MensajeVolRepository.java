package Backend.BackendINF281.Mensajes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Backend.BackendINF281.Mensajes.Models.MensajeVol;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import java.util.List;
import java.util.Optional;


public interface MensajeVolRepository extends JpaRepository<MensajeVol,Integer>{

    Optional<MensajeVol> findByIdpostula(Integer idPostula);

    List<MensajeVol> findByRol(String rol);


    @Modifying
    public void deleteByIdpostula(Integer idrol);


    @Modifying
    @Query("UPDATE MensajeVol u SET  u.estado = 'Rechazado' WHERE u.idpostula = :id")
    void updateRefusedMVol(@Param("id")Integer id);


}


