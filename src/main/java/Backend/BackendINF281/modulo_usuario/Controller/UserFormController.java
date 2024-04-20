package Backend.BackendINF281.modulo_usuario.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.Mensajes.Controller.UserMensajeRolResponse;
import Backend.BackendINF281.Mensajes.Service.MensajesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/messageUser")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
@Tag(name="User_Form_Controller(/messageUser)", description = "Operaciones como: Obtener mensajes de postulantes a Usuarios normales, - Aceptar el mensaje de un postulante a Usuario normal a un rol, - Rechazar el mensaje de un posutlante a Usuario normal, - Inabilitar a un Usuario Normal, - Eliminar a un Usuario Rechazado, - Obtener a posutlantes a Usuarios normales con estado 'Rechazados', - Obtener a postulantes a usuario normales con estado Pendiente, - Obtener a usuarios con estado Habilitado, - Obtener a usuarios con estado Inabilitado, - Obtener todos los mensajes de Usuarios normales que postulan a Donante, - Obtener los mensajes de Usuarios normales que postulan a Donante sin Organizacion, - Obtener los mensajes de Usuarios normales que postulan a Donante con Organizacion, - Aceptar el mensaje de un Usuario normal que postula a un rol,- Rechazar y elimnar automaticamente el mensaje de un Usuario normal que postula a un rol. " )
public class UserFormController {
    
    private final MensajesService mensajeService;

    @Operation(
        summary = "Obtener mensajes de postulantes a Usuarios normales",  
        description = "En la salida: el atributo estado tiene los siguientes valores: Inabilitado, Rechazado, Pendiente  "  
    )
    @GetMapping(value = "getPostUsuario")
    public List<UsuarioPostResponse> obtenerPostUsuario() {
        return mensajeService.obtenerUsersPost();
    }
     
    @Operation(
        summary = "Aceptar el mensaje de un postulante a Usuario normal a un rol",  
        description = ""  
    )
    @GetMapping(value = "acceptUser/{idUser}")
    public ResponseEntity<String> AcceptUser(@PathVariable Integer idUser) {
        return ResponseEntity.ok(mensajeService.acceptUser(idUser));
    }
    
    @Operation(
        summary = " Rechazar el mensaje de un postulante a Usuario normal",  
        description = ""  
    )
    @GetMapping(value = "refusedUser/{id}")
    public ResponseEntity<String> RefusedUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.refusedUser(id));
    }

    @Operation(
        summary = "Inabilitar a un Usuario",  
        description = "IMPORTANTE: solo se puede Inabiltiar a un Usuario que anteriormente ya fue Habilitado, este usuario Inabilitado no podra acceder al sistema. "  
    )
    @GetMapping(value = "inabilitarUser/{id}")
    public ResponseEntity<String> InabilitarUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.inabilitarUser(id));
    }

    @Operation(
        summary = " Eliminar a un Usuario Rechazado",  
        description = "IMPORTANTE: Solo se puede Eliminar a un usuario con estado 'Rechazado'. "  
    )
    @DeleteMapping(value = "deleteUser/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.deleteUser(id));
    }

    @Operation(
        summary = " Obtener a postulantes a Usuarios normales con estado 'Rechazados' ",  
        description = "En salida el atributo 'estado' tendra ese valor: Rechazado "  
    )
    @GetMapping(value="getRechazados")
    public List<UsuarioPostResponse> getRechazados(){
        return mensajeService.obtenerUsersRec();
    }

    @Operation(
        summary = " Obtener a postulantes a usuarios normales con estado Pendiente",  
        description = "En salida el atributo 'estado' tendra ese valor: Pendiente"  
    )
    @GetMapping(value="getPendientes")
    public List<UsuarioPostResponse> getPendiente(){
        return mensajeService.obtenerUsersPend();
    }

    @Operation(
        summary = " Obtener a postulantes a usuarios normales con estado Habilitado",  
        description = "En salida el atributo 'estado' tendra ese valor: Habilitado"  
    )
    @GetMapping(value="getHabilitados")
    public List<UserFormResponse> getHabilitados(){
        return mensajeService.obtenerUsersHab();
    }

    @Operation(
        summary = " Obtener a postulantes a usuarios normales con estado Inabilitado",  
        description = "En salida el atributo 'estado' tendra ese valor: Inabilitado"  
    )
    @GetMapping(value="getInabilitados")
    public List<UserFormResponse> getInabilitados(){
        return mensajeService.obtenerUsersInab();
    }
    

    // Endpoints para obtener a los postulantes al rol de Donante       

    @Operation(
        summary = "Obtener todos los mensajes de Usuarios normales que postulan a Donante ",  
        description = "En la salida tenemos: id del mensaje, datos del usuario y rol = Donante, en la salida se tendran a todos los postulantes a el rol Donante"  
    )
    @GetMapping(value="getPostAllDonantes")
    public List<UserMensajeRolResponse> getAllPostDonantes(){
        return mensajeService.getUserPosDonAll();
    }


    @Operation(
        summary = " Obtener los mensajes de Usuarios normales que postulan a Donante sin Organizacion",  
        description = "En la salida tenemos: id del mensaje, datos del usuario y rol = Donante, en la salida se tendran a todos los postulantes a el rol Donante que no representan a alguna organizacion. "  
    )
    @GetMapping(value="getPostDonantesSinOrg")
    public List<UserMensajeRolResponse> getPostDonantesSinOrg(){
        return mensajeService.getUserPosDonSinOrg();
    }


    @Operation(
        summary = " Obtener los mensajes de Usuarios normales que postulan a Donante con Organizacion",  
        description = "En la salida tenemos: id del mensaje, datos del usuario, rol = Donante y el nombre de la organizacion, en la salida se tendran a todos los postulantes a el rol Donante que representan a una organizacion. "  
    )
    @GetMapping(value="getPostDonantesOrg")
    public List<UserOrgPostResponse> getPostDonantesOrg(){
        return mensajeService.getUserPosDonOrg();
    }

    // Endpoints para obtener a los postulantes al rol de Receptor       

    @Operation(
        summary = "Obtener todos los mensajes de Usuarios normales que postulan a Receptor ",  
        description = "En la salida tenemos: id del mensaje, datos del usuario y rol = Receptor, en la salida se tendran a todos los postulantes a el rol Receptor"  
    )
    @GetMapping(value="getPostAllReceptores")
    public List<UserMensajeRolResponse> getAllPostReceptores(){
        return mensajeService.getUserPosRecAll();
    }

    @Operation(
        summary = " Obtener los mensajes de Usuarios normales que postulan a Receptor sin Organizacion",  
        description = "En la salida tenemos: id del mensaje, datos del usuario y rol = Receptor, en la salida se tendran a todos los postulantes a el rol Receptor que no representan a alguna organizacion. "  
    )
    @GetMapping(value="getPostReceptoresSinOrg")
    public List<UserMensajeRolResponse> getPostReceptoresSinOrg(){
        return mensajeService.getUserPosRecSinOrg();
    }

    @Operation(
        summary = " Obtener los mensajes de Usuarios normales que postulan a Receptor con Organizacion",  
        description = "En la salida tenemos: id del mensaje, datos del usuario, rol = Receptor y el nombre de la organizacion, en la salida se tendran a todos los postulantes a el rol Receptor que representan a una organizacion. "  
    )
    @GetMapping(value="getPostReceptoresOrg")
    public List<UserOrgPostResponse> getPostReceptoresOrg(){
        return mensajeService.getUserPosRecOrg();
    }

    ////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
    // Endpoints para aceptar, rechazar, eliminar mensaje de un posutlante a un respectivo rol 
    @Operation(
        summary = "  Aceptar el mensaje de un Usuario normal que postula a un rol",  
        description = "Acepta el mensaje de un Usuario normal que esta postulando a cualquier rol: Voluntario, Donante, Receptor"  
    )
    @GetMapping(value="acceptMUserRol/{id}")
    public boolean acceptUserRol(@PathVariable Integer id){
        return mensajeService.acceptRolUser(id);
    }


    @Operation(
        summary = "Rechazar y elimnar automaticamente el mensaje de un Usuario normal que postula a un rol",  
        description = "Rechaza y elimina automaticamente el mensaje de un Usuario normal que esta postulando a cualquier rol: Volutnario, Donante, Receptor "  
    )

    @DeleteMapping(value="refusedMUserRol/{id}")
    public boolean refucedUserRol(@PathVariable Integer id){
        return mensajeService.refusedRolUser(id);
    }


    ////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







}
