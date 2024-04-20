package Backend.BackendINF281.DonacionSolicitud.Models;

import Backend.BackendINF281.Inventario.Models.Producto;
import jakarta.persistence.CascadeType;
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
@Table(name="solicita_p")
@IdClass(SolicitaPPK.class)
public class SolicitaP {

    @Id
    @ManyToOne( cascade = {CascadeType.ALL}  )
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Id
    @ManyToOne( cascade = {CascadeType.ALL}  )
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;

    @Column(name="cantidad")
    private Integer cantidadP;

}
