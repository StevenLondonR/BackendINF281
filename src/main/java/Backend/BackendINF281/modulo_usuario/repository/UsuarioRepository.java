package Backend.BackendINF281.modulo_usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Backend.BackendINF281.modulo_usuario.Controller.UsuarioPostResponse;
import Backend.BackendINF281.modulo_usuario.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    //public Optional<Usuario> findByNombre(String nombre);
    @Modifying
    public void deleteByidUsuario(Integer id);

    public Optional<Usuario> findByIdUsuario(Integer idUsuario);

    public Optional<Usuario> findByCorreo(String correo);

    //@Modifying  
    @Query("SELECT new Backend.BackendINF281.modulo_usuario.Controller.UsuarioPostResponse(u.idUsuario, u.nombre, u.apellido, u.correo, u.telefono, u.estado) FROM Usuario u WHERE u.estado = 'Pendiente' OR u.estado = 'Rechazado' OR u.estado = 'Inabilitado' ")   
    List<UsuarioPostResponse> getUsersPost();  
    
    @Modifying
    @Query("UPDATE Usuario u SET u.estado = :estado WHERE u.idUsuario = :id")
    void updateAcceptUser(@Param("id") Integer id, @Param("estado") String estado);

    @Modifying
    @Query("UPDATE Usuario u SET  u.estado = 'Rechazado' WHERE u.idUsuario = :id")
    void updateRefusedUser(@Param("id")Integer id);

    @Modifying
    @Query("UPDATE Usuario u SET  u.estado = 'Inabilitado' WHERE u.idUsuario = :id")
    void updateInabilitarUser(@Param("id") Integer id);    

    @Query("SELECT new Backend.BackendINF281.modulo_usuario.Controller.UsuarioPostResponse(u.idUsuario, u.nombre, u.apellido, u.correo, u.telefono, u.estado) FROM Usuario u WHERE u.estado = :estado ")   
    List<UsuarioPostResponse> getUsersEstado(@Param("estado") String estado);
    




}
