package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrgReceptoraRequest {

    String nombre;
    String apellido;
    String password;
    String correo;
    String ubicacion;
    Integer telefono;
    String user;
    String tipoOrg;
    String ubicacionO;
    String nombreOrg;

}