package Backend.BackendINF281.Mensajes.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import Backend.BackendINF281.Mensajes.Models.MensajeRol;
import java.util.List;


public interface MensajeRolRepository extends JpaRepository<MensajeRol,Integer>{

    Optional<MensajeRol> findByIdrol(Integer idrol);

    List<MensajeRol> findByRol(String rol);

    @Modifying
    public void deleteByIdrol(Integer idrol);

}
