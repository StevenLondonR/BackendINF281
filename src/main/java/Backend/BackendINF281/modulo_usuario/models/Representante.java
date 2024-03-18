package Backend.BackendINF281.modulo_usuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "representante")
public class Representante {

    @Id
    @OneToOne
    @JoinColumn(name = "id_repre", nullable = false, updatable = false )
    private Usuario id_repre;

    @Column
    private Integer id_org_ben;

    @Column
    private Integer id_org_rec;

}

