package Backend.BackendINF281.DonacionSolicitud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaA;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaAPK;

public interface SolicitaARepository extends JpaRepository<SolicitaA,SolicitaAPK>{

}
