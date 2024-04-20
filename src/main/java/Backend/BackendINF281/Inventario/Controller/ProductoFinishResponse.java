package Backend.BackendINF281.Inventario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoFinishResponse {
    Integer iddonacionOsolicitud;
    String tipo;
    Integer cantidad;
    String estado;
}
