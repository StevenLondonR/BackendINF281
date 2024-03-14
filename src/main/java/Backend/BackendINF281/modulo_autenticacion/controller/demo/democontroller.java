package Backend.BackendINF281.modulo_autenticacion.controller.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class democontroller {

    @GetMapping(value="inicio")
    public String inicio(){
        return "Pagina principal ";
    }


}
