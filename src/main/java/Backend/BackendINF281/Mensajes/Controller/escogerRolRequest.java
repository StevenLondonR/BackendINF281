package Backend.BackendINF281.Mensajes.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class escogerRolRequest {

    String correo;
    String rol;
    String contenido;

}
