package Backend.BackendINF281.modulo_usuario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoluntarioAllResponse {

    String nombreUser;
    String apellido;
    String correo;
    Integer telefono;
    String estadoGeneralUser;   /// Hobilitado, inabilitado
    String subrol;

}
