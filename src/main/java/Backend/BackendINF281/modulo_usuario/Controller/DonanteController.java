package Backend.BackendINF281.modulo_usuario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.modulo_usuario.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/donanteUser")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class DonanteController {


    private final UserService userService;

    @GetMapping(value="getAllDonantes")
    public List<DonanteAllReponse> getAllDonantes() {
        return userService.listAllDonantes();
    }

    
    

}
