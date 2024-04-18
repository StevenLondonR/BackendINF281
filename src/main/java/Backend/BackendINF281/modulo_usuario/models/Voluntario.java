package Backend.BackendINF281.modulo_usuario.models;

import java.util.List;

import Backend.BackendINF281.DonacionSolicitud.Models.Donacion;
import Backend.BackendINF281.DonacionSolicitud.Models.Solicitud;
import Backend.BackendINF281.Mensajes.Models.MensajeVol;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voluntario")
public class Voluntario {

    @Id
    @Column(name = "id_voluntario")
    private Integer idvoluntario;
    
    @Column
    private String rol;

    @OneToMany(mappedBy = "postulav", cascade = {CascadeType.ALL},orphanRemoval= true )
    private List<MensajeVol> listMensajesV;

    @OneToMany(mappedBy = "voluntario", cascade = {CascadeType.ALL},orphanRemoval= true )
    private List<Donacion> listDonacionesVolR; /// lista de donaciones de un responsable 

    @OneToMany(mappedBy = "voluntario", cascade = {CascadeType.ALL},orphanRemoval= true )  
    private List<Solicitud> listSolicitudesVolR;  /// lista de donaciones de un responsable 

    @ManyToMany(mappedBy = "listVoluntariosColab", cascade = {CascadeType.ALL} )  
    private List<Donacion> listDonacionesVolC;   // lista de donaciones de un colaborador

    @ManyToMany(mappedBy = "listVoluntariosColab", cascade = {CascadeType.ALL})
    private List<Solicitud> listSolicitudesVolV;  // lista de solicitudes de un colaborador


}
