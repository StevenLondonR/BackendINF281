package Backend.BackendINF281.modulo_usuario.models;

import Backend.BackendINF281.Organizaciones.models.OrganizacionBenefica;
import Backend.BackendINF281.Organizaciones.models.OrganizacionReceptora;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "representante")
public class Representante {
    
    @Id
    private Integer id_usuario;

    @Nullable
    @ManyToOne
    @JoinColumn(name ="id_org_ben")
    private OrganizacionBenefica id_org_ben;

    @Nullable
    @ManyToOne
    @JoinColumn(name="id_org_rec")
    private OrganizacionReceptora id_org_rec;

}



