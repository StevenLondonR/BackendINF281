package Backend.BackendINF281.Mensajes.Models;

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

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensaje_vol")
public class MensajeVol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_postula")
    private Integer idpostula;

    private String rol;

    private String estado;  /// valores: Pendiente, Rechazado 

    @ManyToOne
    @JoinColumn(name="id_voluntario")
    private Voluntario postulav;



}
