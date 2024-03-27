package Backend.BackendINF281.Organizaciones.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="organizacion_receptora")
public class OrganizacionReceptora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_org_rec;

    @Column
    private String tipo_org;

    @Column
    private String Ubicacion;

    @Column
    private String nombre_org;

}
