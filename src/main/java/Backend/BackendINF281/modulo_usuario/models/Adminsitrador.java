package Backend.BackendINF281.modulo_usuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "administrador")
public class Adminsitrador {

    @Id
    @OneToOne
    @JoinColumn(name = "id_admin", nullable = false, updatable = false )
    private Usuario id_admin;

    @Column
    private String ci;

}
