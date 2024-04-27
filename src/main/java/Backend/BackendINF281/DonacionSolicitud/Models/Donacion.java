package Backend.BackendINF281.DonacionSolicitud.Models;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import Backend.BackendINF281.modulo_usuario.models.Donante;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="donacion")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion")
    private Integer iddonacion;

    private Integer cantidad;

    private String tipo_ap;
    @Column(name ="fecha_hora_adquisicion")
    @Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar fecha_hora_adquisicion;

    @Column(name = "cant_requerida_vol")
    private Integer cantidadReqVol;

    @Column(name = "nro_voluntarios")
    private Integer nroVoluntariosC;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Donante usuario;
    
    @ManyToOne
    @JoinColumn(name="id_voluntario")
    private Voluntario voluntario;      /// Responsable de reocger la donacion, si esta vacio la donacion esta en estado: SinResponsable 


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( 

        name = "recoge",
        joinColumns = @JoinColumn(name="id_donacion", referencedColumnName = "id_donacion" ),
        inverseJoinColumns = @JoinColumn(name="id_voluntario", referencedColumnName = "id_voluntario")
        
    )
    private List<Voluntario> listVoluntariosColab; /// lista de voluntarios colaboradores, si la cantidad de colaboradores no esta llena esta en estado: Pendiente

    @OneToMany(mappedBy = "donacion", cascade = {CascadeType.ALL}  ,orphanRemoval= true )
    private List<ContieneA> listRelacionAli;  /// Si existe algun alimento en esta lista, la donacion esta en estado: Realizado
    
    @OneToMany(mappedBy = "donacion",cascade = {CascadeType.ALL}  ,orphanRemoval= true)
    private List<ContieneP> listRelacionProd; /// Si existe algun producto en esta lista, la donacion esta en estado: Realizado
    
}
