package Backend.BackendINF281.modulo_usuario.models;

import java.util.List;

import Backend.BackendINF281.DonacionSolicitud.Models.Solicitud;
import Backend.BackendINF281.Organizaciones.models.OrganizacionReceptora;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receptor")
public class Receptor {

    @Id
    @Column(name = "id_usuario")
    private Integer idusuario;

    @ManyToOne
    @JoinColumn(name = "id_org_rec")
    private OrganizacionReceptora orgRec; 

    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> listSolicitudesR;


}
