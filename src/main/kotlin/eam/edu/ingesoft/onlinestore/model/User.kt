package eam.edu.ingesoft.onlinestore.model

import eam.edu.ingesoft.onlinestore.model.City
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_usuarios")
data class User(
    @Id
    @Column(name = "id_usuario")
    val id: String,

    @Column(name = "direccion")
    var address: String,

    @Column(name = "nombre")
    var name: String,

    @Column(name = "apellido")
    var lastName: String,

    @ManyToOne
    @JoinColumn(name="id_ciudad")
    val city: City
) : Serializable
