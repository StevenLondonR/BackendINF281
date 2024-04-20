package Backend.BackendINF281.DonacionSolicitud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.DonacionSolicitud.Models.ContieneP;
import Backend.BackendINF281.DonacionSolicitud.Models.ContienePPK;

public interface ContienePRepository extends JpaRepository<ContieneP,ContienePPK>{

}
