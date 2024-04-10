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
import Backend.BackendINF281.modulo_usuario.repository.DonanteRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class UserController {

    private final UserService userService;
    
    @PostMapping(value = "getSimpleUser")
    public ResponseEntity<UserSimpleResponse> obtenerSimpleUsuario(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.getUserSimple(request));
    }

}
