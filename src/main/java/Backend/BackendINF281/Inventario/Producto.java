package Backend.BackendINF281.Inventario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    private String estado;

    private String tipo;

    private Integer cantidad;

    
    @ManyToOne
    @JoinColumn(name="id_historial")
    private HistorialDeshecho historialDeshecho;

}
