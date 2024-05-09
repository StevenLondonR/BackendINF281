package Backend.BackendINF281.Inventario.Controller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoSolFinishResponse {
    
    Integer idsolitud;
    Integer idAlimento;
    // TODO se elimino Integer cantidad;
    
}
