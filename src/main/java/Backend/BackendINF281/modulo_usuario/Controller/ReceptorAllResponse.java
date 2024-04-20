package Backend.BackendINF281.modulo_usuario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceptorAllResponse {
    String nombreUser;
    String apellidoUser;
    String correo;
    Integer telefono; 
    String estado;
    String nombreOrg;

}
