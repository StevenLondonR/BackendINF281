package Backend.BackendINF281.NuestraOrganizacion.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Backend.BackendINF281.NuestraOrganizacion.model.NuestraOrganizacion;
import jakarta.transaction.Transactional;
import java.util.List;


public interface NuestraOrgRepository extends JpaRepository<NuestraOrganizacion,Integer>{

    public Optional<NuestraOrganizacion> findByIdorganizacion(Integer id);
    
    @Modifying
    @Query("UPDATE NuestraOrganizacion d SET d.mision = COALESCE(:mision, d.mision) , d.vision = COALESCE(:vision, d.vision) , d.quehacemos = COALESCE( :quehacemos, d.quehacemos) , d.imagenes=:imagenes WHERE d.idorganizacion = :id_organizacion")    
    void updateOrg(@Param("id_organizacion") Integer idorganizacion, @Param("mision") String mision, @Param("vision") String vision, @Param("quehacemos") String quehacemos );    
    

/*
    @Query("select d.mision from 'datos_organizacion' d where d.id_organizacion = 1")
    String updateDatos();
*/
}
