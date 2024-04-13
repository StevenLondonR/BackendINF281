package Backend.BackendINF281.Inventario.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name="deshecho_a")
@IdClass(deshechaAPK.class)
public class deshechaA {

    
    @Id
    @ManyToOne
    @JoinColumn(name="id_alimento")
    private Alimento alimento;

    @Id
    @ManyToOne
    @JoinColumn(name="id_historial")
    private HistorialDeshecho historialDeshecho;

    @Column(name = "cantidad")
    private Integer cantidadDA;
    
}
