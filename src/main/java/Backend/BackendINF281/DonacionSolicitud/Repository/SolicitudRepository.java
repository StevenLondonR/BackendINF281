package Backend.BackendINF281.DonacionSolicitud.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.DonacionSolicitud.Models.Solicitud;


public interface SolicitudRepository extends JpaRepository<Solicitud,Integer>{

    public Optional<Solicitud> findByIdsolicitud(Integer insolicitud);

}
