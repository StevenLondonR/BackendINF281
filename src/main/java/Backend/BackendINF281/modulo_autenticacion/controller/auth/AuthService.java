package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.Organizaciones.Repository.OrgBeneficaRepository;
import Backend.BackendINF281.Organizaciones.Repository.OrgReceptoraRepository;
import Backend.BackendINF281.Organizaciones.models.OrganizacionBenefica;
import Backend.BackendINF281.Organizaciones.models.OrganizacionReceptora;
import Backend.BackendINF281.modulo_autenticacion.jwt.JwtService;
import Backend.BackendINF281.modulo_usuario.models.Representante;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import Backend.BackendINF281.modulo_usuario.repository.RepreRepository;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import Backend.BackendINF281.modulo_usuario.repository.VoluntarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepo;
    private final JwtService jwtservice;
    private final VoluntarioRepository volRepo;
    private final OrgBeneficaRepository orgBenefica;
    private final RepreRepository repreRepo;
    private final PasswordEncoder passwordEncoder;
    private final OrgReceptoraRepository orgReceptora;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        
        UserDetails user=userRepo.findByCorreo(request.getCorreo()).orElseThrow();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        String token=jwtservice.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

        //return null;
        
    }

    public AuthResponse registerVol(VoluntarioRequest request) {

        
        Usuario user=Usuario.builder() 
                .nombre(request.getNombre()) 
                .apellido(request.getApellido()) 
                .contraseña(passwordEncoder.encode(request.getPassword())) 
                .correo(request.getCorreo())
                .ubicacion(request.getUbicacion()) 
                .telefono(request.getTelefono()) 
                .build(); 

        userRepo.save(user);

        Voluntario vol=Voluntario.builder()
                .id_voluntario(user.getId_usuario())
                .turno(request.getTurno())
                .horario(request.getHorario())
                .edad(request.getEdad())
                .build();

        volRepo.save(vol);

        return AuthResponse.builder()
            .token(jwtservice.getToken(user))
            .build();
    }
    
    public AuthResponse registerBen(OrgBeneficaRequest request) {

        Usuario user=Usuario.builder() 
                .nombre(request.getNombre()) 
                .apellido(request.getApellido()) 
                .contraseña(passwordEncoder.encode(request.getPassword())) 
                .correo(request.getCorreo())
                .ubicacion(request.getUbicacion()) 
                .telefono(request.getTelefono()) 
                .build(); 

        userRepo.save(user);

        OrganizacionBenefica orgBen=OrganizacionBenefica.builder()
                .Ubicacion(request.getUbicacionO())
                .tipo_a(request.getTipoAlimento())
                .area_servicio(request.getAreaServicio())
                .nombre_org(request.getNombreOrg())
                .build();
        
        orgBenefica.save(orgBen);

        Representante repre=Representante.builder()
                .id_usuario(user.getId_usuario())
                .id_org_ben(orgBen)
                .id_org_rec(null)
                .build();
        repreRepo.save(repre);
        
        return AuthResponse.builder()
            .token(jwtservice.getToken(user))
            .build();
    }
    
    public AuthResponse registerRec(OrgReceptoraRequest request) {

        Usuario user=Usuario.builder() 
                .nombre(request.getNombre()) 
                .apellido(request.getApellido()) 
                .contraseña(passwordEncoder.encode(request.getPassword())) 
                .correo(request.getCorreo())
                .ubicacion(request.getUbicacion()) 
                .telefono(request.getTelefono()) 
                .build(); 

        userRepo.save(user);

        OrganizacionReceptora orgRec=OrganizacionReceptora.builder()
                .tipo_org(request.getTipoOrg())
                .Ubicacion(request.getUbicacionO())
                .nombre_org(request.getNombreOrg())
                .build();
        
        orgReceptora.save(orgRec);

        Representante repre=Representante.builder()
                .id_usuario(user.getId_usuario())
                .id_org_ben(null)
                .id_org_rec(orgRec)
                .build();
        repreRepo.save(repre);
        
        return AuthResponse.builder()
            .token(jwtservice.getToken(user))
            .build();
    }

}
