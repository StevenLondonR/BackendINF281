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
public class deshechaAPK implements Serializable{

    private Alimento alimento;

    private HistorialDeshecho historialDeshecho;

}
