package Backend.BackendINF281.DonacionSolicitud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.DonacionSolicitud.Models.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion,Integer>{

}
