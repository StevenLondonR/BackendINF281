package Backend.BackendINF281.modulo_usuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "voluntario")
public class Voluntario {

    @Id
    @OneToOne
    @JoinColumn(name = "id_voluntario", nullable = false, updatable = false )
    private Usuario id_voluntario;

    @Column
    private String turno;

    @Column
    private String horario;

    @Column
    private Integer edad;


}
