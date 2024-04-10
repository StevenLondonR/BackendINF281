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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/messageUser")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class UserFormController {
    
    private final MensajesService mensajeService;

    @GetMapping(value = "getPostUsuario")
    public List<UsuarioPostResponse> obtenerPostUsuario() {
        return mensajeService.obtenerUsersPost();
    }
     
    @GetMapping(value = "acceptUser/{id}")
    public ResponseEntity<String> AcceptUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.acceptUser(id));
    }
    
    
    @GetMapping(value = "refusedUser/{id}")
    public ResponseEntity<String> RefusedUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.refusedUser(id));
    }

    @GetMapping(value = "inabilitarUser/{id}")
    public ResponseEntity<String> InabilitarUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.inabilitarUser(id));
    }

    @DeleteMapping(value = "deleteUser/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(mensajeService.deleteUser(id));
    }

    @GetMapping(value="getRechazados")
    public List<UsuarioPostResponse> getRechazados(){
        return mensajeService.obtenerUsersRec();
    }

    @GetMapping(value="getPendientes")
    public List<UsuarioPostResponse> getPendiente(){
        return mensajeService.obtenerUsersPend();
    }

    @GetMapping(value="getHabilitados")
    public List<UserFormResponse> getHabilitados(){
        return mensajeService.obtenerUsersHab();
    }

    @GetMapping(value="getInabilitados")
    public List<UserFormResponse> getInabilitados(){
        return mensajeService.obtenerUsersInab();
    }
    

    // Endpoints para obtener a los postulantes al rol de Donante       


    @GetMapping(value="getPostAllDonantes")
    public List<UserMensajeRolResponse> getAllPostDonantes(){
        return mensajeService.getUserPosDonAll();
    }

    @GetMapping(value="getPostDonantesSinOrg")
    public List<UserMensajeRolResponse> getPostDonantesSinOrg(){
        return mensajeService.getUserPosDonSinOrg();
    }

    @GetMapping(value="getPostDonantesOrg")
    public List<UserOrgPostResponse> getPostDonantesOrg(){
        return mensajeService.getUserPosDonOrg();
    }

    ////////////////////////////////////////////////////////////////

    // Endpoints para aceptar, rechazar, eliminar mensaje de un posutlante a un respectivo rol 

    @GetMapping(value="acceptMUserRol/{id}")
    public boolean acceptUserRol(@PathVariable Integer id){
        return mensajeService.acceptRolUser(id);
    }


    @DeleteMapping(value="refusedMUserRol/{id}")
    public boolean refucedUserRol(@PathVariable Integer id){
        return mensajeService.refucedRolUser(id);
    }


    ////////////////////////////////////////////////////////////////

    

}
