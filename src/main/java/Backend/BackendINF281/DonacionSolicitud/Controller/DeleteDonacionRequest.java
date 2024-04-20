package Backend.BackendINF281.DonacionSolicitud.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDonacionRequest {

    String correo;
    Integer idDonacion;

}
