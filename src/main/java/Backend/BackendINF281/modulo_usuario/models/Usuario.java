package Backend.BackendINF281.modulo_usuario.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Backend.BackendINF281.Mensajes.Models.MensajeRol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    String nombre;

    private String apellido;

    private String contraseña;

    private String ubicacion;

    String correo;

    private Integer telefono;
    
    @Column
    String estado;    
/*
    @OneToMany(mappedBy = "postular")
    private MensajeRol mensajesRol;*/

    /* 
    @OneToOne(mappedBy = "id_admin")
    private Adminsitrador administrador;

    @OneToOne(mappedBy = "id_usuario")
    private Representante representante;

    @OneToOne(mappedBy = "id_voluntario")
    private Voluntario voluntario;
    */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return contraseña;
    }

}
