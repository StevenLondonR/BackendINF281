package Backend.BackendINF281.modulo_usuario.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.modulo_usuario.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Usuario_Controller(/user)", description = "Operaciones como: Obtener los datos importantes de un usuario")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class UserController {

    private final UserService userService;
    
    @Operation(
        summary = "Obtener los datos importantes de un usuario.",
        description = "IMPORTANTE: En la salida pueden existir atributos vacios \"\" "
    )
    @PostMapping(value = "getSimpleUser")
    public ResponseEntity<UserSimpleResponse> obtenerSimpleUsuario(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.getUserSimple(request));
    }

}
