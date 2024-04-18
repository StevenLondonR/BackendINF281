package Backend.BackendINF281.modulo_usuario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.modulo_usuario.Service.UserService;
import Backend.BackendINF281.modulo_usuario.Service.VoluntarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/voluntarioUser")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class VoluntarioController {

    private final UserService userService;
    private final VoluntarioService voluntarioService;


    @GetMapping(value="getAllVoluntarios")
    public List<VoluntarioAllResponse> getAllVoluntarios() {
        return userService.obtenerAllVoluntarios();
    }

    @PostMapping(value="escogerDonResponsable")
    public boolean escogerDonResponsable(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.escogerDonacionResponsable(request);
    }
    
    @PostMapping(value="quitarDonResponsable")
    public boolean quitarDonResponsable(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.quitarDonacionResponsable(request);
    }

    @PostMapping(value="establecerNroVolC")
    public boolean establecerNroVolC(@RequestBody ResponsableNroVolRequest request) {
        return voluntarioService.establecerNroVolC(request);
    }
    ////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value="escogerDonColaborador")
    public boolean escogerDonColaborador(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.escogerDonacionColaborador(request);
    }

    @PostMapping(value="quitarDonColaborador")
    public boolean quitarDonColaborador(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.quitarDonacionColaborador(request);
    }

}
