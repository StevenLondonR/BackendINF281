package Backend.BackendINF281.DonacionSolicitud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaP;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaPPK;

public interface SolicitaPRepository extends JpaRepository<SolicitaP,SolicitaPPK>{

}
