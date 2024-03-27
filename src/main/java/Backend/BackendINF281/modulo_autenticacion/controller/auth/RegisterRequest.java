package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String nombre;
    String apellido;
    String password;
    String correo;
    String ubicacion;
    int telefono;

}
