package Backend.BackendINF281.Inventario.Models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class deshechaPPK implements Serializable{

    private Producto producto;

    private HistorialDeshecho historialDeshecho;

}
