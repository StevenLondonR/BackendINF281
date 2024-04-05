package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.modulo_autenticacion.jwt.JwtService;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepo;
    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {  
        Usuario userR=userRepo.findByCorreo(request.getCorreo()).orElseThrow();
        if(userR.getEstado().equals("Habilitado")){  
            UserDetails user=(UserDetails) userR;
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
            String token=jwtservice.getToken(user);   
            
            return AuthResponse.builder()  
                .token(token)  
                .build();  
        }
        return null;
        
        
    }

    public String registerVol(RegisterRequest request) {  

        Usuario user=Usuario.builder() 
                .nombre(request.getNombre()) 
                .apellido(request.getApellido()) 
                .contraseña(passwordEncoder.encode(request.getPassword()))   
                .correo(request.getCorreo())  
                .ubicacion(request.getUbicacion()) 
                .telefono(request.getTelefono())  
                .estado("Pendiente") 
                .build();  
        userRepo.save(user);  

        return "Se envio la solicitud"; 

    }
    

}






