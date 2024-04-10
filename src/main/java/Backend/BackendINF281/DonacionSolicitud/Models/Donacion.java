package Backend.BackendINF281.DonacionSolicitud.Models;

import java.sql.Date;

import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="donacion")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion")
    private Integer iddonacion;

    private Integer cantidad;

    private String tipo_ap;

    private Date fecha_hora_adquisicion;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="id_voluntario")
    private Voluntario voluntario;

}
