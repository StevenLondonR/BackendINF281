package Backend.BackendINF281.DonacionSolicitud.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonacionResponse {

    Integer idDonacion;  // de la entidad usuario
    String nombreU;      // de la entidad usuario
    String apellidoU;    // de la entidad usuario
    Integer telefonoU;   // de la entidad usuario
    Integer cantidad;  // de la entidad donacion
    String tipo_ap;     // de la entidad donacion
    String fechaHoraProg; // de la entidad donacion
    String estado;  // SinResponsable, Pendiente, Realizado
    String ubicacion;
    String correoResponsable;  // de la entidad Voluntario
    Integer nroRequeridoCol;   // de la entidad donacion
    Integer nroColaboradores;  // de la entidad donacion

}
