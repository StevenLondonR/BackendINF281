package Backend.BackendINF281.DonacionSolicitud.Models;

import Backend.BackendINF281.Inventario.Models.Alimento;
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
@Table(name="contiene_a")
@IdClass(ContieneAPK.class)
public class ContieneA {
    
    @Id
    @ManyToOne( cascade = {CascadeType.ALL}  )
    @JoinColumn(name="id_alimento")
    private Alimento alimento;

    @Id
    @ManyToOne( cascade = {CascadeType.ALL})
    @JoinColumn(name="id_donacion")
    private Donacion donacion;
    

    @Column(name="cantidad")
    private Integer cantidadA;

}
