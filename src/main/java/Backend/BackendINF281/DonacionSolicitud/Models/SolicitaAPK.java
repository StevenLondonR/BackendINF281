package Backend.BackendINF281.DonacionSolicitud.Models;

import java.io.Serializable;

import Backend.BackendINF281.Inventario.Models.Alimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitaAPK implements Serializable{

    private Alimento alimento;

    private Solicitud solicitud;

}
