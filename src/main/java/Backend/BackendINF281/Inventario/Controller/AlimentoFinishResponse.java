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

    Integer iddonacionOsolicitud;
    String tipo;
    String fecha_Vencimiento;
    Integer cantidad;
    String estado;

}
