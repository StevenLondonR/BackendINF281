package Backend.BackendINF281.modulo_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.modulo_usuario.models.Voluntario;


public interface VoluntarioRepository extends JpaRepository<Voluntario,Integer>{

    Optional<Voluntario> findByIdvoluntario(Integer idvoluntario);
    
}
