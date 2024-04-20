package Backend.BackendINF281.DonacionSolicitud.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponse {

    Integer idSolicitud;  // de la entidad usuario
    String nombreU;      // de la entidad usuario
    String apellidoU;    // de la entidad usuario
    Integer telefonoU;   // de la entidad usuario
    Integer cantidad;  // de la entidad Solicitud
    String tipo_ap;     // de la entidad Solicitud
    String fechaHoraProg; // de la entidad Solicitud
    String estado;  // SinResponsable, Pendiente, Realizado
    String correoResponsable;  // de la entidad Voluntario
    Integer nroRequeridoCol;   // de la entidad Solicitud
    Integer nroColaboradores;  // de la entidad Solicitud

}
