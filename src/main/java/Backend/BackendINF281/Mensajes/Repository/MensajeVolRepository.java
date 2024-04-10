package Backend.BackendINF281.Mensajes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.Mensajes.Models.MensajeVol;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import java.util.List;


public interface MensajeVolRepository extends JpaRepository<MensajeVol,Integer>{

    ///MensajeVol findByPostulav(Voluntario postulav);

}
