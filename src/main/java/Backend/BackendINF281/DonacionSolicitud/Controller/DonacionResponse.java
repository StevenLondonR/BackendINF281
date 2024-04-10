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

    Integer idDon;
    String nombreU;
    String apellidoU;
    Integer telefonoU; 
    Integer cantidad;
    String tipo_ap;
    String fechaHoraProg;
    String estado;  // sinVoluntarios, Pendiente, Realizado


}
