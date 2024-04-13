package Backend.BackendINF281.DonacionSolicitud.Models;

import Backend.BackendINF281.Inventario.Models.Producto;
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
@Table(name="contiene_p")
@IdClass(ContienePPK.class)
public class ContieneP {

    @Id
    @ManyToOne
    @JoinColumn(name="id_donacion")
    private Donacion donacion;

    @Id
    @ManyToOne
    @JoinColumn(name="id_producto")
    private Producto producto;

    @Column(name="cantidad")
    private Integer cantidadP;
    
}
