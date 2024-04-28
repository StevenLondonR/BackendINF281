package Backend.BackendINF281.Inventario.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoFinishResponse {

    Integer iddonacion;
    String tipo;
    String nombreAlimento;
    String fecha_Vencimiento;
    Integer cantidad;
    String estado;

}
