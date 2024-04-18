package Backend.BackendINF281.modulo_usuario.Controller;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonanteAllReponse {

    String nombreUser;
    String apellidoUser;
    String correo;
    Integer telefono; 
    String estado;
    String nombreOrg;

}
