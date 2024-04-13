package Backend.BackendINF281.Inventario.Models;

import java.util.List;

import Backend.BackendINF281.DonacionSolicitud.Models.ContieneP;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaP;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    private String estado;

    private String tipo;

    private Integer cantidad;

    @OneToMany(mappedBy = "producto")
    private List<ContieneP> listRelacionDon;

    @OneToMany(mappedBy = "producto")
    private List<SolicitaP> listRelacionSol;

    @OneToMany(mappedBy = "producto")
    private List<deshechaP> listHistorialProd;


}
