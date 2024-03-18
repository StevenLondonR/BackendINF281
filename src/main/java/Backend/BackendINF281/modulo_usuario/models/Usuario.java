package Backend.BackendINF281.modulo_usuario.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String contraseña;

    @Column
    private String ubicacion;

    @Column
    private String correo;

    @Column
    private Integer telefono;


    @OneToOne(mappedBy = "id_admin", cascade= CascadeType.ALL)
    private Adminsitrador administrador;

    @OneToOne(mappedBy = "id_repre", cascade= CascadeType.ALL)
    private Representante representante;

    @OneToOne(mappedBy = "id_voluntario", cascade= CascadeType.ALL)
    private Voluntario voluntario;



}
