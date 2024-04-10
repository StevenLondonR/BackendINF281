package Backend.BackendINF281.modulo_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.modulo_usuario.models.Receptor;

public interface ReceptorRepository extends JpaRepository<Receptor,Integer>{

    Optional<Receptor> findByIdusuario(Integer idusuario);

}
