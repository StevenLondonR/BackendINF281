package Backend.BackendINF281.Inventario.Models;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import Backend.BackendINF281.DonacionSolicitud.Models.ContieneA;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaA;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Column(name="id_alimento")
    private Integer idalimento;

    private String tipo;

    @Column(name="fecha_venc")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaVenc;

    private Integer cantidad;

    private String estado;

    @OneToMany(mappedBy = "alimento", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<ContieneA> listRelacionDon;

    @OneToMany(mappedBy = "alimento", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<SolicitaA> listRelacionSol;

    
    
    @OneToMany(mappedBy = "alimento", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<deshechaA> listHistorialAlim;
 
}
