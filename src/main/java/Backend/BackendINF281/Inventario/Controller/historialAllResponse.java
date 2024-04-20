package Backend.BackendINF281.Inventario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class historialAllResponse {

    Integer iddonacionOsolicitud;
    String tipo;
    Integer cantidad;
    String estado;
    String fecha_de_deshecho;


}
