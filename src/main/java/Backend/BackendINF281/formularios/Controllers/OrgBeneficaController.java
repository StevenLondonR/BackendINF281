package Backend.BackendINF281.formularios.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class OrgBeneficaController {

    @GetMapping(value = "prueba1")
    public String registrar(){

        return "se registra una organizacion benefica ";

    }

    @PostMapping(value = "prueba2")
    public String obtener(){
        return "se obtine datos de la organizacion benefica";
    }

}
