package Backend.BackendINF281.DonacionSolicitud.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditDonacionRequest {

    String correo;
    Integer idDonacion;
    Integer cantidad;
    String tipo_ap;
    String fechaHoraRecogida;

}
