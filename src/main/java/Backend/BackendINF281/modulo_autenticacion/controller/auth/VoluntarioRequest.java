package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioRequest {

    String nombre;
    String apellido;
    String password;
    String correo;
    String ubicacion;
    Integer telefono;
    Integer edad;
    String turno;
    String horario;

}
