package Backend.BackendINF281.DonacionSolicitud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.DonacionSolicitud.Models.ContieneA;
import Backend.BackendINF281.DonacionSolicitud.Models.ContieneAPK;

public interface ContieneARepository extends JpaRepository<ContieneA,ContieneAPK>{

}
