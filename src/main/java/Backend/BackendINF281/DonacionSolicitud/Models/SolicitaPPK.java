package Backend.BackendINF281.DonacionSolicitud.Models;

import java.io.Serializable;

import Backend.BackendINF281.Inventario.Models.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitaPPK implements Serializable{

    private Producto producto;

    private Solicitud solicitud;

}
