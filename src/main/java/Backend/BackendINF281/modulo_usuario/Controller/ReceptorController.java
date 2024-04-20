package Backend.BackendINF281.modulo_usuario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.modulo_usuario.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/receptorUser")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
@Tag(name="Receptor_Controller(/receptorUser)", description = "Operaciones como: obtener a todos los Usuario con rol Receptor" )
public class ReceptorController {

    private final UserService userService;


    @Operation(
        summary = "Obtener a todos los usuarios Receptores ",  
        description = "En la salida: el atributo 'estado' puede tener los siguientes valores: Habilitado, Inabilitado. - El atributo 'nombreOrg' puede tener el valor vacio \' \' "  
    )
    @GetMapping(value="getAllReceptores")
    public List<ReceptorAllResponse> getAllReceptores() {
        return userService.listAllReceptor();
    }

}
