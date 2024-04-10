package Backend.BackendINF281.modulo_usuario.models;

import java.util.List;

import Backend.BackendINF281.Mensajes.Models.MensajeVol;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
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

    @OneToMany(mappedBy = "postulav")
    private List<MensajeVol> mensajeV;


}
