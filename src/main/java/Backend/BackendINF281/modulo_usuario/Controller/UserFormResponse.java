package Backend.BackendINF281.modulo_usuario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFormResponse {

    Integer id;
    String nombre;
    String apellido;
    String correo;
    Integer telefono;
    String rol;
    String estado;

}
