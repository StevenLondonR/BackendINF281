package Backend.BackendINF281.Inventario;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="historial_deshecho")
public class HistorialDeshecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historial;

    private Date fecha_deshecho;

    private String razon;

    private Integer cantidad;

}
