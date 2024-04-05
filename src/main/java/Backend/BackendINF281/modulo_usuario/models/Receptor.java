package Backend.BackendINF281.modulo_usuario.models;

import Backend.BackendINF281.Organizaciones.models.OrganizacionReceptora;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Integer id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_org_rec")
    private OrganizacionReceptora orgRec; 

}
