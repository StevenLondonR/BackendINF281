package Backend.BackendINF281.modulo_usuario.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Backend.BackendINF281.Mensajes.Models.MensajeVol;
import Backend.BackendINF281.Mensajes.Repository.MensajeVolRepository;
import Backend.BackendINF281.Organizaciones.models.OrganizacionBenefica;
import Backend.BackendINF281.Organizaciones.models.OrganizacionReceptora;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import Backend.BackendINF281.modulo_usuario.Controller.UserSimpleResponse;
import Backend.BackendINF281.modulo_usuario.models.Donante;
import Backend.BackendINF281.modulo_usuario.models.Receptor;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import Backend.BackendINF281.modulo_usuario.repository.DonanteRepository;
import Backend.BackendINF281.modulo_usuario.repository.ReceptorRepository;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import Backend.BackendINF281.modulo_usuario.repository.VoluntarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final DonanteRepository donanteRepository;
    private final VoluntarioRepository voluntarioRepository; 
    private final ReceptorRepository receptorRepository;

    public UserSimpleResponse getUserSimple(UserRequest request) {  // solo obtiene los datos mas importantes de un usuario
        
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user != null){
            String nomOrg="";
            String rol="";
            String rolV="";
            Donante don1=donanteRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
            Voluntario vol1=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);
            Receptor recept1=receptorRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
            
            if(don1 != null){
                OrganizacionBenefica orgBen=don1.getOrgBen();
                if(orgBen != null){
                    nomOrg=orgBen.getNombre_org();
                }
                rol="Donante";
            }
            if(vol1 != null){
                String rols=vol1.getRol();
                if(!rols.equals(null) || !rols.equals("")){
                    rolV=rols;
                }
                if(!rol.equals("")){
                    rol=",";
                }

                rol="Voluntario";
            }
            if(recept1 != null){
                OrganizacionReceptora orgRec=recept1.getOrgRec();
                if(orgRec != null){
                    nomOrg=orgRec.getNombre_org();
                }
                if(!rol.equals("")){
                    rol=",";
                }
                rol="Receptor";
            }
            UserSimpleResponse simpleUser=UserSimpleResponse.builder()
                                    .nombre(user.getNombre())
                                    .apellido(user.getApellido())
                                    .correo(user.getCorreo())
                                    .telefono(user.getTelefono())
                                    .rol(rol)
                                    .rolVol(rolV)
                                    .nombreOrg(nomOrg)
                                    .build();
            return simpleUser;

        }

        return null;
    }

}
