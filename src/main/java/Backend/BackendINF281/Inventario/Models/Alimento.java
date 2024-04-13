package Backend.BackendINF281.Inventario.Models;

import java.sql.Date;
import java.util.List;

import Backend.BackendINF281.DonacionSolicitud.Models.ContieneA;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaA;
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
@Table(name="alimento")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alimento;

    private String tipo;

    private Date fecha_venc;

    private Integer cantidad;

    private String estado;

    @OneToMany(mappedBy = "alimento")
    private List<ContieneA> listRelacionDon;

    @OneToMany(mappedBy = "alimento")
    private List<SolicitaA> listRelacionSol;

    
    
    @OneToMany(mappedBy = "alimento")
    private List<deshechaA> listHistorialAlim;
 
}
