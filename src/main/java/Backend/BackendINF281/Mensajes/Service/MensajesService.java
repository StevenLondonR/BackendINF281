package Backend.BackendINF281.Mensajes.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.Mensajes.Controller.UserMensajeRolResponse;
import Backend.BackendINF281.Mensajes.Controller.escogerRolRequest;
import Backend.BackendINF281.Mensajes.Models.MensajeRol;
import Backend.BackendINF281.Mensajes.Repository.MensajeRolRepository;
import Backend.BackendINF281.Organizaciones.Repository.OrgBeneficaRepository;
import Backend.BackendINF281.Organizaciones.Repository.OrgReceptoraRepository;
import Backend.BackendINF281.Organizaciones.models.OrganizacionBenefica;
import Backend.BackendINF281.Organizaciones.models.OrganizacionReceptora;
import Backend.BackendINF281.modulo_autenticacion.jwt.JwtService;
import Backend.BackendINF281.modulo_usuario.Controller.UserFormResponse;
import Backend.BackendINF281.modulo_usuario.Controller.UserOrgPostResponse;
import Backend.BackendINF281.modulo_usuario.Controller.UsuarioPostResponse;
import Backend.BackendINF281.modulo_usuario.models.Donante;
import Backend.BackendINF281.modulo_usuario.models.Receptor;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import Backend.BackendINF281.modulo_usuario.repository.DonanteRepository;
import Backend.BackendINF281.modulo_usuario.repository.ReceptorRepository;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import Backend.BackendINF281.modulo_usuario.repository.VoluntarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensajesService {


    private final UsuarioRepository UserRepository;
    private final DonanteRepository DonRepository;
    private final ReceptorRepository RecRepository;
    private final VoluntarioRepository VolRepository;
    private final MensajeRolRepository mRolRepository;
    private final OrgBeneficaRepository orgBeneficaRepository;
    private final OrgReceptoraRepository orgReceptoraRepository;


    public List<UsuarioPostResponse> obtenerUsersPost(){

        return UserRepository.getUsersPost();
        //return null;
    }

    @Transactional
    public String acceptUser(Integer id) {
        UserRepository.updateAcceptUser(id,"Habilitado");
        return "Usuario Aceptado";
    }

    @Transactional
    public String refusedUser(Integer id) {
        UserRepository.updateRefusedUser(id);
        return "Usuario Rechazado";
    }

    @Transactional
    public String inabilitarUser(Integer id) {
        UserRepository.updateInabilitarUser(id);
        return "Usuario Inabilitado";
    }
    @Transactional
    public String deleteUser(Integer id) {
        UserRepository.deleteByidUsuario(id);
        return "Usuario Eliminado";
    }

    public List<UsuarioPostResponse> obtenerUsersRec(){
        return UserRepository.getUsersEstado("Rechazado");
        //return null;
    }    
    public List<UsuarioPostResponse> obtenerUsersPend(){

        return UserRepository.getUsersEstado("Pendiente");
        //return null;
    }
    
    public List<UserFormResponse> obtenerUsersInab(){
        
        List<UsuarioPostResponse> userp= UserRepository.getUsersEstado("Inabilitado");
        List<UserFormResponse> userR=new ArrayList<>();
        
        for(int i=0;i<userp.size();i++){
            String rol="";
            Donante don=DonRepository.findByIdusuario(userp.get(i).getId()).orElse(null);
            Voluntario vol=VolRepository.findByIdvoluntario(userp.get(i).getId()).orElse(null);
            Receptor rec=RecRepository.findByIdusuario(userp.get(i).getId()).orElse(null);
            
            if(don != null){
                rol=rol+"Donante";
            }

            if(vol != null){
                if(!rol.equals("")){
                    rol=rol+",";
                }
                rol=rol+"Voluntario";
            }

            if(rec != null){
                if(!rol.equals("")){
                    rol=rol+",";
                }
                rol=rol+"Receptor";
            }

            UserFormResponse user=UserFormResponse.builder()
                        .id(userp.get(i).getId())
                        .nombre(userp.get(i).getNombre())
                        .apellido(userp.get(i).getApellido())
                        .correo(userp.get(i).getCorreo())
                        .telefono(userp.get(i).getTelefono())
                        .rol(rol)
                        .estado(userp.get(i).getEstado())
                        .build();
            userR.add(user);

        }
        
        
        return userR;
    }    
    public List<UserFormResponse> obtenerUsersHab(){

        List<UsuarioPostResponse> userp= UserRepository.getUsersEstado("Habilitado");
        List<UserFormResponse> userR=new ArrayList<>();
        
        for(int i=0;i<userp.size();i++){
            String rol="";
            Donante don=DonRepository.findByIdusuario(userp.get(i).getId()).orElse(null);
            Voluntario vol=VolRepository.findByIdvoluntario(userp.get(i).getId()).orElse(null);
            Receptor rec=RecRepository.findByIdusuario(userp.get(i).getId()).orElse(null);
            
            if(don != null){
                rol=rol+"Donante";
            }

            if(vol != null){
                if(!rol.equals("")){
                    rol=rol+",";
                }
                rol=rol+"Voluntario";
            }

            if(rec != null){
                if(!rol.equals("")){
                    rol=rol+",";
                }
                rol=rol+"Receptor";
            }

            UserFormResponse user=UserFormResponse.builder()
                        .id(userp.get(i).getId())
                        .nombre(userp.get(i).getNombre())
                        .apellido(userp.get(i).getApellido())
                        .correo(userp.get(i).getCorreo())
                        .telefono(userp.get(i).getTelefono())
                        .rol(rol)
                        .estado(userp.get(i).getEstado())
                        .build();
            userR.add(user);

        }
        
        
        return userR;
    }

    public List<UserFormResponse> getUserPosVol(){
        // TODO Realizar la obtencion de todos los postulantes a Voluntarios
        return null;
    }

    public List<UserMensajeRolResponse> getUserPosDonAll(){

        List<MensajeRol> mensajesR=mRolRepository.findByRol("Donante");
        //System.out.println(mensajesR);
        List<UserMensajeRolResponse> listUserp=new ArrayList<>();

        for(int i=0;i< mensajesR.size();i++){
                UserMensajeRolResponse userP=UserMensajeRolResponse.builder()
                    .id(mensajesR.get(i).getIdrol())
                    .nombre(mensajesR.get(i).getPostular().getNombre())
                    .apellido(mensajesR.get(i).getPostular().getApellido())
                    .correo(mensajesR.get(i).getPostular().getCorreo())
                    .telefono(mensajesR.get(i).getPostular().getTelefono())
                    .rol(mensajesR.get(i).getRol())
                    .build();
                listUserp.add(userP);
                //System.out.println(listUserp);
        }
        
        return listUserp;
    }

    public List<UserMensajeRolResponse> getUserPosDonSinOrg(){
        List<MensajeRol> mensajesR=mRolRepository.findByRol("Donante");

        List<UserMensajeRolResponse> listUserp=new ArrayList<>();

        for(int i=0;i< mensajesR.size();i++){
            if(mensajesR.get(i).getContenido().equals(null) || mensajesR.get(i).getContenido().equals("")){
                UserMensajeRolResponse userP=UserMensajeRolResponse.builder()
                    .id(mensajesR.get(i).getIdrol())
                    .nombre(mensajesR.get(i).getPostular().getNombre())
                    .apellido(mensajesR.get(i).getPostular().getApellido())
                    .correo(mensajesR.get(i).getPostular().getCorreo())
                    .telefono(mensajesR.get(i).getPostular().getTelefono())
                    .rol(mensajesR.get(i).getRol())
                    .build();
                listUserp.add(userP);
            }

        }
        return listUserp;
    }

    public List<UserOrgPostResponse> getUserPosDonOrg(){
        List<MensajeRol> mensajesR=mRolRepository.findByRol("Donante");

        List<UserOrgPostResponse> listUserp=new ArrayList<>();

        for(int i=0;i< mensajesR.size();i++){   
                
                String prueba=mensajesR.get(i).getContenido();

                String[] Org=prueba.split(",");
            if(Org.length>1){
                String nombreOrg=Org[2];
                
                UserOrgPostResponse userP=UserOrgPostResponse.builder()
                    .id(mensajesR.get(i).getIdrol())
                    .nombre(mensajesR.get(i).getPostular().getNombre())
                    .apellido(mensajesR.get(i).getPostular().getApellido())
                    .correo(mensajesR.get(i).getPostular().getCorreo())
                    .telefono(mensajesR.get(i).getPostular().getTelefono())
                    .rol(mensajesR.get(i).getRol())
                    .nombreOrg(nombreOrg)
                    .build();
                listUserp.add(userP);
            }

        }
        return listUserp;

    }

    public List<UsuarioPostResponse> getUserPosRecAll(){
        // TODO Realizar la obtencion de todos los postulantes a receptor
        return null;
    }

    public List<UsuarioPostResponse> getUserPosRecSinOrg(){
        // TODO Realizar la obtencion de todos los postulantes a receptor sin organizaciones
        return null;
    }
    public List<UserOrgPostResponse> getUserPosRecOrg(){
        // TODO Realizar la obtencion de todos los postulantes a receptor con organizaciones 
        return null;
    }

    public Boolean escogerRol(escogerRolRequest request) {

        Boolean salida=false;

        Usuario userPos=UserRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(userPos != null){
            MensajeRol mensajeRo=MensajeRol.builder()
                    .rol(request.getRol())
                    .contenido(request.getContenido())
                    .postular(userPos)
                    .build();

            mRolRepository.save(mensajeRo);
            MensajeRol msecu=mRolRepository.findByIdrol(mensajeRo.getIdrol()).orElse(null);
            if(msecu != null){
                salida=true;
            }

        }    

        return salida;
    }

    @Transactional
    public boolean acceptRolUser(Integer idMensajeRol) { // Acepta a un usuario que postulo a un rol: Donante, Receptor, Voluntario

        MensajeRol mrol=mRolRepository.findByIdrol(idMensajeRol).orElse(null);
        boolean resul=false;

        if(mrol != null){
            Usuario user1=mrol.getPostular();
            if(mrol.getRol().equalsIgnoreCase("Donante")){
                String[] contend=mrol.getContenido().split(","); /// 0:Ubicacion , 1:tipo_a , 2:nombre_org , 3:area_servicio     
                OrganizacionBenefica orgB=null;
                if(contend.length>2){ /// verifica si tiene contenido, si tiene es un representante de una organizacion benefica y guardara los datos de la organizacion benfica 
                    orgB=OrganizacionBenefica.builder()
                                .Ubicacion(contend[0])
                                .tipo_a(contend[1])
                                .nombre_org(contend[2])
                                .area_servicio(contend[3])
                                .build();
                    orgBeneficaRepository.save(orgB);
                }

                Donante don1=Donante.builder()  // contruimos los datos
                            .idusuario(user1.getIdUsuario())
                            .orgBen(orgB)
                            .build();
                DonRepository.save(don1); /// guarda los datos 

                Donante verificarD=DonRepository.findByIdusuario(user1.getIdUsuario()).orElse(null);
                if(verificarD != null){
                    resul=true;
                }
                mRolRepository.deleteByIdrol(idMensajeRol);

            }else if(mrol.getRol().equalsIgnoreCase("Receptor")){
                
                String[] contend=mrol.getContenido().split(","); /// 0:Ubicacion , 1:tipo_Organizacion , 2:nombre_org    
                OrganizacionReceptora orgR=null;
                if(contend.length>1){ /// verifica si tiene contenido, si tiene es un representante de una organizacion benefica y guardara los datos de la organizacion benfica 
                    orgR=OrganizacionReceptora.builder()
                                .Ubicacion(contend[0])
                                .tipo_org(contend[1])
                                .nombre_org(contend[2])
                                .build();
                    orgReceptoraRepository.save(orgR);
                }

                Receptor rec1=Receptor.builder()  // contruimos los datos
                            .idusuario(user1.getIdUsuario())
                            .orgRec(orgR)
                            .build();
                RecRepository.save(rec1); /// guarda los datos 

                Receptor verificarR=RecRepository.findByIdusuario(user1.getIdUsuario()).orElse(null);
                if(verificarR != null){
                    resul=true;
                }
                mRolRepository.deleteByIdrol(idMensajeRol);


            }else if(mrol.getRol().equalsIgnoreCase("Voluntario")){

                Voluntario vol1=Voluntario.builder()  // contruimos los datos
                            .idvoluntario(user1.getIdUsuario())
                            .rol("")
                            .build();
                VolRepository.save(vol1); /// guarda los datos 

                Voluntario verificarV=VolRepository.findByIdvoluntario(user1.getIdUsuario()).orElse(null);
                if(verificarV != null){
                    resul=true;
                }
                mRolRepository.deleteByIdrol(idMensajeRol);
            }
            
        }

        return resul;

    }

    @Transactional
    public boolean refucedRolUser(Integer idMensajeRol) {
        
        mRolRepository.deleteByIdrol(idMensajeRol);
        MensajeRol verificarR=mRolRepository.findByIdrol(idMensajeRol).orElse(null);
        boolean resul=false;
        if(verificarR == null){
            resul=true;
        }

        return resul;
    }



}
