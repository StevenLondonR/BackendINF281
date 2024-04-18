package Backend.BackendINF281.DonacionSolicitud.Models;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import Backend.BackendINF281.modulo_usuario.models.Receptor;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
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
@Table(name="solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idsolicitud;

    private Integer cantidad;

    private String tipo_ap;

    @Column(name="fecha_hora_prog")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha_hora_prog;

    @Column(name = "cant_requerida_vol")
    private Integer cantidadReqVol;

    @Column(name = "nro_voluntarios")
    private Integer nroVoluntariosC;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Receptor usuario;
    
    @ManyToOne
    @JoinColumn(name="id_voluntario")
    private Voluntario voluntario;

    @ManyToMany
    @JoinTable(
        name = "entrega",
        joinColumns = @JoinColumn(name="id_solicitud", referencedColumnName = "id_solicitud" ),
        inverseJoinColumns = @JoinColumn(name="id_voluntario", referencedColumnName = "id_voluntario")
    )
    private List<Voluntario> listVoluntariosColab; /// lista de voluntarios colaboradores


    @OneToMany(mappedBy = "solicitud")
    private List<SolicitaA> listRelacionProd;

}
