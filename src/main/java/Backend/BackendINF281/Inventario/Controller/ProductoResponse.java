package Backend.BackendINF281.Inventario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    Integer idProducto;
    String tipo;
    String estado;
    Integer cantidad;

}
