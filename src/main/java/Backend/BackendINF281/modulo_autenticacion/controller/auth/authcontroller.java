package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authcontroller {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.login(request));

    }

    @PostMapping(value = "registerVol")
    public ResponseEntity<AuthResponse> registerVoluntario(@RequestBody VoluntarioRequest request){
        return ResponseEntity.ok(authService.registerVol(request));
    }
    
    @PostMapping(value="registerOB")
    public ResponseEntity<AuthResponse> registerOBenefica(@RequestBody OrgBeneficaRequest request) {
        return ResponseEntity.ok(authService.registerBen(request));
    }

    @PostMapping(value="registerOR")
    public ResponseEntity<AuthResponse> registerOReceptora(@RequestBody OrgReceptoraRequest request) {

        return ResponseEntity.ok(authService.registerRec(request));
    }
    
    



}
