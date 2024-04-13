package Backend.BackendINF281.DonacionSolicitud.Models;

import java.sql.Date;
import java.util.List;

import Backend.BackendINF281.modulo_usuario.models.Donante;
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

    private Date fecha_hora_adquisicion;

    @Column(name = "cant_requerida_vol")
    private Integer cantidadReqVol;

    @Column(name = "nro_voluntarios")
    private Integer nroVoluntariosC;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Donante usuario;
    
    @ManyToOne
    @JoinColumn(name="id_voluntario")
    private Voluntario voluntario;


    @ManyToMany
    @JoinTable(
        name = "recoge",
        joinColumns = @JoinColumn(name="id_donacion", referencedColumnName = "id_donacion" ),
        inverseJoinColumns = @JoinColumn(name="id_voluntario", referencedColumnName = "id_voluntario")
        
    )
    private List<Voluntario> listVoluntariosColab; /// lista de voluntarios colaboradores

    @OneToMany(mappedBy = "donacion")
    private List<ContieneA> listRelacionAli;
    
    @OneToMany(mappedBy = "donacion")
    private List<ContieneP> listRelacionProd;
    
}
