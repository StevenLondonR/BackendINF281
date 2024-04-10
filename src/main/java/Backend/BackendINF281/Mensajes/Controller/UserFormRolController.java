package Backend.BackendINF281.Mensajes.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.Mensajes.Service.MensajesService;
import lombok.RequiredArgsConstructor;

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

    


}
