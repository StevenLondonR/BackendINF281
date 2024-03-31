package Backend.BackendINF281.modulo_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.modulo_usuario.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    public Optional<Usuario> findByNombre(String nombre);

    public Optional<Usuario> findByCorreo(String correo);

}
