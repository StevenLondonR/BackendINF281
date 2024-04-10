package Backend.BackendINF281.modulo_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.modulo_usuario.models.Donante;
import java.util.List;


public interface DonanteRepository extends JpaRepository<Donante,Integer>{

    Optional<Donante> findByIdusuario(Integer idusuario);
}
