package Backend.BackendINF281.modulo_usuario.Service;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.DonacionSolicitud.Models.Donacion;
import Backend.BackendINF281.DonacionSolicitud.Repository.DonacionRepository;
import Backend.BackendINF281.modulo_usuario.Controller.ResponsableNroVolRequest;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import Backend.BackendINF281.modulo_usuario.Controller.VolEscogerDonRequest;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import Backend.BackendINF281.modulo_usuario.repository.VoluntarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoluntarioService {

    private final UsuarioRepository UserRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final DonacionRepository donacionRepository;

    @Transactional
    public boolean escogerDonacionResponsable(VolEscogerDonRequest request){
        boolean salida=false;

        Usuario user=UserRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user != null){
            Voluntario vol=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);
            if(vol != null){
                donacionRepository.updateRolResponsable(request.getIdDonacion(), vol);
        
                Donacion don1=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
        
                if(don1.getVoluntario() != null){
                    salida=true;
                }
            }
            
        }
        

        return salida;
    }

    @Transactional
    public boolean quitarDonacionResponsable(VolEscogerDonRequest request){
        boolean salida=false;
        
        Usuario user=UserRepository.findByCorreo(request.getCorreo()).orElse(null);

        Voluntario vol=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        Donacion don2=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);

        
        if(vol == don2.getVoluntario()){
           // donacionRepository.updateRolResponsable(request.getIdDonacion(), null);
            don2.setVoluntario(null);
            donacionRepository.save(don2);
            Donacion don1=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
            //System.out.println("/////// Voluntario eliminado");
            //System.out.println(don1.getVoluntario());
            if(don1.getVoluntario() == null){
                salida=true;
            }

        }

        return salida;
    }

    /// modificar automaticamente le numero de voluntarios que se postulan 
    @Transactional
    public boolean establecerNroVolC(ResponsableNroVolRequest request){
        boolean salida=false;
        Usuario user1=UserRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user1 != null){
            Voluntario vol1=voluntarioRepository.findByIdvoluntario(user1.getIdUsuario()).orElse(null);
            if(vol1 != null){
                String[] rol=vol1.getRol().split(",");
                if(rol.length > 0 ){
                    if(rol[0].equals("Responsable") || rol[1].equals("Responsable")){

                        Donacion don1=donacionRepository.findByIddonacion(request.getIddonacion()).orElse(null);
                        if(don1!=null){
                            if(don1.getVoluntario() == vol1){
                                int antCantidadVol=don1.getCantidadReqVol();
                                don1.setCantidadReqVol(request.getNroVoluntarios());
                                donacionRepository.save(don1);
                                Donacion don2=donacionRepository.findByIddonacion(request.getIddonacion()).orElse(null);
                                if(antCantidadVol<don2.getCantidadReqVol()){
                                    salida =true;
                                }
                            }
                        }

                    }
                }

            }


        }


        return salida;
    }   


//-------------------------------------COLABORADOR------------------------
    @Transactional
    public boolean escogerDonacionColaborador(VolEscogerDonRequest request){
        boolean salida=false;

        Usuario user=UserRepository.findByCorreo(request.getCorreo()).orElse(null);

        Voluntario vol=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        Donacion don1=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
        Integer longAnterior=don1.getListVoluntariosColab().size();
        if(!don1.getListVoluntariosColab().contains(vol)){
        
            //donacionRepository.updateRolResponsable(request.getIdDonacion(), vol);
            if(don1.getNroVoluntariosC()<don1.getCantidadReqVol() && don1.getCantidadReqVol()!=0){ //// un voluntario Colaborador solo va a poder escoger una donacion si la cantidad de voluntarios requeridos no es 0, o que la cantidad de voluntarios requeridos no este lleno
                don1.getListVoluntariosColab().add(vol);
                don1.setNroVoluntariosC(don1.getListVoluntariosColab().size());
                donacionRepository.save(don1);
                Donacion don2=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
                if(don2.getListVoluntariosColab().size() > longAnterior){
                    salida=true;
                }
            }

        }

        return salida;
    }

    @Transactional
    public boolean quitarDonacionColaborador(VolEscogerDonRequest request){
        boolean salida=false;

        Usuario user=UserRepository.findByCorreo(request.getCorreo()).orElse(null);

        Voluntario vol=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        Donacion don1=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
        int longAnterior=don1.getListVoluntariosColab().size();
        if(don1.getListVoluntariosColab().contains(vol)){
        
            //donacionRepository.updateRolResponsable(request.getIdDonacion(), vol);
            don1.getListVoluntariosColab().remove(vol);
            don1.setNroVoluntariosC(don1.getListVoluntariosColab().size());
            donacionRepository.save(don1);
            Donacion don2=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
            if(don2.getListVoluntariosColab().size() < longAnterior){
                salida=true;
            }
        }

        return salida;
    }


}
