package Backend.BackendINF281.Mensajes.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.Mensajes.Service.MensajesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name="Usuario_Form_Rol_Controller(/userPostRol)", description = " Operaciones como: Un usuario normal puede escoger un ROL(Voluntario, Receptor, Donante), - Obtener todos los mensajes de usuarios que postulan a Voluntario, - Un usuario voluntario puede escoger su subrol(Responsable, Colaborador), - Aceptar el mensaje de un usuario voluntario que postula(Responsable, Colaborador), - Rechazar el mensaje de un usuario voluntario que postula(Responsable, Colaborador), - Eliminar el mensaje de un usuario Volutnario que postula(Responsable, Colaborador), - Obtener a todos los mensajes de los usuario voluntario que postulan(Responsable, Colaborador)" )
@RestController
@RequestMapping("/userPostRol")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class UserFormRolController {

    private final MensajesService mensajeService;

    @Operation(
        summary = "Un usuario normal puede escoger un ROL(Voluntario, Receptor, Donante)",
        description="IMPORTANTE: Todos los ejemplos estan en el README de github "
    )
    @PostMapping(value = "escogerRol")
    public Boolean escogerRol(@RequestBody escogerRolRequest request){
        return mensajeService.escogerRol(request);
    }


    @Operation(
        summary="Obtener todos los mensajes de usuarios que postulan a Voluntario",
        description="IMPORTANTE: En la salida el atributo 'rol' puede tener los siguientes valores: Responsable, Colaborador "
    )
    @GetMapping(value= "getAllPostulantesRolVol")
    public List<UserMensajeRolResponse> getAllPostulantesVol() {
        return mensajeService.getUserPostVolAll();
    }


    @Operation(
        summary = "Un usuario normal puede escoger un ROL(Voluntario, Receptor, Donante)",
        description="IMPORTANTE: En la entrada de datos el atributo 'subrol' puede tener los siguinetes valores: Colaborador, Responsable"
    )
    @PostMapping(value="escogerSubRolVol")
    public boolean escogerSubRolVol(@RequestBody RolVolRequest request) {
        return mensajeService.registrarseRolVol(request);
    }



    @Operation(
        summary = "Aceptar el mensaje de un usuario voluntario que postula(Responsable, Colaborador)",
        description=""
    )
    @GetMapping(value = "acceptMessageUserVol/{idMensaje}")
    public boolean acceptUserVol(@PathVariable Integer idMensaje) {
        return mensajeService.acceptUserVol(idMensaje);
    }
    
    
    @Operation(
        summary = "Rechazar el mensaje de un usuario voluntario que postula(Responsable, Colaborador)",
        description=""
    )
    @GetMapping(value = "refusedMessageUserVol/{idMensaje}")
    public boolean refusedUserVol(@PathVariable Integer idMensaje) {
        return mensajeService.refusedUserVol(idMensaje);
    }


    @Operation(
        summary = "Eliminar el mensaje de un usuario Volutnario que postula(Responsable, Colaborador)",
        description="IMPORTANTE: Solo se puede eliminar los mensaje que tienen el estado: Rechazado" 
    )
    @DeleteMapping(value = "deleteMessageUserVol/{idMensaje}")
    public boolean deleteMessageUserVol(@PathVariable Integer idMensaje) {
        return mensajeService.deleteUserVol(idMensaje);
    }

    @Operation(
        summary = "Obtener a todos los mensajes de los usuario voluntario que postulan(Responsable, Colaborador)",
        description="IMPORTANTE: En la entrada el atributo 'estadoMensajeSubRol' es el estado del mensaje(Rechazado, Pendiente), el atributo 'subrol' puede tener los valores: Colaborador, Responsable"
    )
    @GetMapping(value="getAllPostulantesSubRolVol")
    public List<UserSubRolVolResponse> getAllPostulantesSubRolVol() {  ///  se obtiene a los voluntarios que postulan a un subRol: Responsable, Voluntario
        return mensajeService.getAllPostulantesSubRolVol();
    }   
    

}
