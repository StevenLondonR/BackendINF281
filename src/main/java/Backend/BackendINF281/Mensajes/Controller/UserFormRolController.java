package Backend.BackendINF281.Mensajes.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.Mensajes.Service.MensajesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/userPostRol")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class UserFormRolController {

    private final MensajesService mensajeService;

    @PostMapping(value = "escogerRol")
    public Boolean escogerRol(@RequestBody escogerRolRequest request){
        return mensajeService.escogerRol(request);
    }

    @GetMapping(value= "getAllPostulantesRolVol")
    public List<UserMensajeRolResponse> getAllPostulantesVol() {
        return mensajeService.getUserPostVolAll();
    }
    
    @PostMapping(value="escogerSubRolVol")
    public boolean escogerSubRolVol(@RequestBody RolVolRequest request) {
        return mensajeService.registrarseRolVol(request);
    }

    @GetMapping(value = "acceptMessageUserVol/{idMensaje}")
    public boolean acceptUserVol(@PathVariable Integer idMensaje) {
        return mensajeService.acceptUserVol(idMensaje);
    }
    
    @GetMapping(value = "refusedMessageUserVol/{idMensaje}")
    public boolean refusedUserVol(@PathVariable Integer idMensaje) {
        return mensajeService.refusedUserVol(idMensaje);
    }

    @DeleteMapping(value = "deleteMessageUserVol/{idMensaje}")
    public boolean deleteMessageUserVol(@PathVariable Integer idMensaje) {
        return mensajeService.deleteUserVol(idMensaje);
    }
    @GetMapping(value="getAllPostulantesSubRolVol")
    public List<UserSubRolVolResponse> getAllPostulantesSubRolVol() {  ///  se obtiene a los voluntarios que postulan a un subRol: Responsable, Voluntario
        return mensajeService.getAllPostulantesSubRolVol();
    }   
    

}
