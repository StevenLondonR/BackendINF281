package Backend.BackendINF281.modulo_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.modulo_usuario.models.Adminsitrador;

public interface AdminRepository extends JpaRepository<Adminsitrador,Integer>{

    

}   
