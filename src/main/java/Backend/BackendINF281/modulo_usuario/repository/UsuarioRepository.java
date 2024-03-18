package Backend.BackendINF281.modulo_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.modulo_usuario.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

}
