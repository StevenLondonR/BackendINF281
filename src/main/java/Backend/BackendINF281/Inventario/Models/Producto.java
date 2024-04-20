package Backend.BackendINF281.Inventario.Models;

import java.util.List;

import Backend.BackendINF281.DonacionSolicitud.Models.ContieneP;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaP;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    @Column(name="id_producto")
    private Integer idproducto;

    private String estado;

    private String tipo;

    private Integer cantidad;

    @OneToMany(mappedBy = "producto", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<ContieneP> listRelacionDon;

    @OneToMany(mappedBy = "producto", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<SolicitaP> listRelacionSol;

    @OneToMany(mappedBy = "producto", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<deshechaP> listHistorialProd;


}
