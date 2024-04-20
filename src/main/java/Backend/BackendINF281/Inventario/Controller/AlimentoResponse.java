package Backend.BackendINF281.Inventario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoResponse {

    Integer idAlimento;
    String tipo;
    String fecha_Vencimiento;
    Integer cantidad;
    String estado;

}
