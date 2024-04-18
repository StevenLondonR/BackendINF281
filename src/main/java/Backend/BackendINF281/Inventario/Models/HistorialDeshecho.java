package Backend.BackendINF281.Inventario.Models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(name = "id_historial")
    private Integer idhistorial;

    private Date fecha_deshecho;

    private String razon;

    private Integer cantidad;

    
    @OneToMany(mappedBy = "historialDeshecho")
    private List<deshechaP> listProd;

    
    @OneToMany(mappedBy = "historialDeshecho")
    private List<deshechaA> listAlim;

}

