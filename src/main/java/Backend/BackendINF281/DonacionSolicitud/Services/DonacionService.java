package Backend.BackendINF281.DonacionSolicitud.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import Backend.BackendINF281.DonacionSolicitud.Controller.DeleteDonacionRequest;
import Backend.BackendINF281.DonacionSolicitud.Controller.DonacionRequest;
import Backend.BackendINF281.DonacionSolicitud.Controller.DonacionResponse;
import Backend.BackendINF281.DonacionSolicitud.Controller.EditDonacionRequest;
import Backend.BackendINF281.DonacionSolicitud.Models.ContieneA;
import Backend.BackendINF281.DonacionSolicitud.Models.ContieneP;
import Backend.BackendINF281.DonacionSolicitud.Models.Donacion;
import Backend.BackendINF281.DonacionSolicitud.Models.Solicitud;
import Backend.BackendINF281.DonacionSolicitud.Repository.ContieneARepository;
import Backend.BackendINF281.DonacionSolicitud.Repository.ContienePRepository;
import Backend.BackendINF281.DonacionSolicitud.Repository.DonacionRepository;
import Backend.BackendINF281.DonacionSolicitud.Repository.SolicitaARepository;
import Backend.BackendINF281.DonacionSolicitud.Repository.SolicitaPRepository;
import Backend.BackendINF281.Inventario.Controller.AlimentoFinishResponse;
import Backend.BackendINF281.Inventario.Controller.ProductoFinishResponse;
import Backend.BackendINF281.Inventario.Models.Alimento;
import Backend.BackendINF281.Inventario.Models.Producto;
import Backend.BackendINF281.Inventario.Repository.AlimentoRepository;
import Backend.BackendINF281.Inventario.Repository.ProductoRepository;
import Backend.BackendINF281.Mensajes.Controller.RolVolRequest;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import Backend.BackendINF281.modulo_usuario.models.Donante;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import Backend.BackendINF281.modulo_usuario.repository.DonanteRepository;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import Backend.BackendINF281.modulo_usuario.repository.VoluntarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final DonacionRepository donacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final DonanteRepository donanteRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final AlimentoRepository alimentoRepository;
    private final ProductoRepository productoRepository;
    private final ContieneARepository contieneARepository;
    private final ContienePRepository contienePRepository;
    

    public List<DonacionResponse> getAll() throws ParseException { /// lista todas las donaciones 
        List<Donacion> listDon=donacionRepository.findAll();
        List<DonacionResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listDon.size();i++){
            Usuario user=usuarioRepository.findByIdUsuario(listDon.get(i).getUsuario().getIdusuario()).orElse(null);
            String respon="";
            Voluntario vol1 = listDon.get(i).getVoluntario() ;
            //System.out.println(vol1);
            if(vol1 != null){
                Usuario user2=usuarioRepository.findByIdUsuario(vol1.getIdvoluntario()).orElse(null);
                if(user2 != null){
                    respon=user2.getCorreo();
                }
            }
            Calendar cal=listDon.get(i).getFecha_hora_adquisicion();

                
            DonacionResponse donResp=DonacionResponse.builder()
                            .idDonacion(listDon.get(i).getIddonacion())
                            .nombreU(user.getNombre())
                            .apellidoU(user.getApellido())
                            .telefonoU(user.getTelefono())
                            .cantidad(listDon.get(i).getCantidad())
                            .tipo_ap(listDon.get(i).getTipo_ap())
                            .fechaHoraProg(convertGregorianDate(cal)) // convertido a la salida => dd/MM/yyyy HH:mm:ss 
                            .estado(verificarEstadoDonacion(listDon.get(i)))
                            .correoResponsable(respon)
                            .ubicacion(listDon.get(i).getUbicacion())
                            .nroRequeridoCol(listDon.get(i).getCantidadReqVol())
                            .nroColaboradores(listDon.get(i).getNroVoluntariosC())
                            .build();
            listResponse.add(donResp);
        }
        return listResponse;
    }

    private String convertGregorianDate(Calendar calendar) throws ParseException{  
        String inputDateStr = calendar.getTime().toString(); /// string de entrada => Mon Sep 02 20:30:00 BOT 2024

        // Parse the input string to a Date object
        SimpleDateFormat inputParser = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date parsedDate = inputParser.parse(inputDateStr);

        // Create a SimpleDateFormat object for the desired output format
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

        // Format the parsed date to the desired format
        String formattedDate = outputFormatter.format(parsedDate).toString();
        return formattedDate;   /// salida esta en formato => 02/09/2024 20:30:00

    }

    private String verificarEstadoDonacion(Donacion donacion) {  // verifica el estado de una donacion
        String salida="";    
        if(donacion.getVoluntario() != null){
            salida="Pendiente";
        }else{
            salida="SinResponsable";
        }
        if(donacion.getListRelacionAli().size() > 0 || donacion.getListRelacionProd().size() > 0){
            salida="Realizado";
        }
        return salida;
    }

    private List<DonacionResponse> getDonaciones(String tipo) throws ParseException{  // lista por: SinResponsable,Pendiente, Realizado
        List<Donacion> listDon=donacionRepository.findAll();
        List<DonacionResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listDon.size();i++){
            if(verificarEstadoDonacion(listDon.get(i)).equalsIgnoreCase(tipo) ){
                Usuario user=usuarioRepository.findByIdUsuario(listDon.get(i).getUsuario().getIdusuario()).orElse(null);
                Voluntario vol1=listDon.get(i).getVoluntario();
                String respon="";
                if(vol1 != null){
                    Usuario user2=usuarioRepository.findByIdUsuario(vol1.getIdvoluntario()).orElse(null);
                    
                    if(user2 != null){
                        respon=user2.getCorreo();
                    }
                }

                DonacionResponse donResp=DonacionResponse.builder()
                                .idDonacion(listDon.get(i).getIddonacion())
                                .nombreU(user.getNombre())
                                .apellidoU(user.getApellido())
                                .telefonoU(user.getTelefono())
                                .cantidad(listDon.get(i).getCantidad())
                                .tipo_ap(listDon.get(i).getTipo_ap())
                                .fechaHoraProg(convertGregorianDate(listDon.get(i).getFecha_hora_adquisicion())) // TODO veficar la posicion de los datos al convertir a string 
                                .estado(verificarEstadoDonacion(listDon.get(i)))
                                .correoResponsable(respon)
                                .ubicacion(listDon.get(i).getUbicacion())
                                .nroRequeridoCol(listDon.get(i).getCantidadReqVol())
                                .nroColaboradores(listDon.get(i).getNroVoluntariosC())
                                .build();
                listResponse.add(donResp);
            }
        }
        return listResponse;
    }

    private List<DonacionResponse> getDonaciones(List<Donacion> listDonations, String tipo) throws ParseException{
        List<Donacion> listDon=new ArrayList<>();
        listDon.addAll(listDonations);
        List<DonacionResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listDon.size();i++){
            if(verificarEstadoDonacion(listDon.get(i)).equalsIgnoreCase(tipo) || tipo.equalsIgnoreCase("ALL")){
                Usuario user=usuarioRepository.findByIdUsuario(listDon.get(i).getUsuario().getIdusuario()).orElse(null);
                Voluntario vol1=listDon.get(i).getVoluntario();
                String respon="";
                if(vol1 != null){
                    Usuario user2=usuarioRepository.findByIdUsuario(vol1.getIdvoluntario()).orElse(null);
                    if(user2 != null){
                        respon=user2.getCorreo();
                    }
                }

                DonacionResponse donResp=DonacionResponse.builder()
                                .idDonacion(listDon.get(i).getIddonacion())
                                .nombreU(user.getNombre())
                                .apellidoU(user.getApellido())
                                .telefonoU(user.getTelefono())
                                .cantidad(listDon.get(i).getCantidad())
                                .tipo_ap(listDon.get(i).getTipo_ap())
                                .fechaHoraProg(convertGregorianDate(listDon.get(i).getFecha_hora_adquisicion())) // TODO veficar la posicion de los datos al convertir a string 
                                .estado(verificarEstadoDonacion(listDon.get(i)))
                                .correoResponsable(respon)
                                .nroRequeridoCol(listDon.get(i).getCantidadReqVol())
                                .nroColaboradores(listDon.get(i).getNroVoluntariosC())
                                .ubicacion(listDon.get(i).getUbicacion())
                                .build();
                listResponse.add(donResp);
            }
        }
        return listResponse;
    }

    public List<DonacionResponse> getDonacionesResponsable(UserRequest request) throws ParseException{

        List<DonacionResponse> listResponse=new ArrayList<>();
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        Voluntario vol1=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        if(vol1 != null){

            List<Donacion> listDonR=vol1.getListDonacionesVolR();
            listResponse.addAll(getDonaciones(listDonR,"ALL"));

        }

        return listResponse;
    }
    
    public List<DonacionResponse> getDonacionesColaborador(UserRequest request) throws ParseException{

        List<DonacionResponse> listResponse=new ArrayList<>();
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        Voluntario vol1=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        if(vol1 != null){

            List<Donacion> listDonC=vol1.getListDonacionesVolC();
            listResponse.addAll(getDonaciones(listDonC,"ALL"));

        }

        return listResponse;
    }
    // TODO realizar getDonaciones para donaciones realizadas, pendientes para usuarios voluntarios Responsables y colaboradores. 


    public List<DonacionResponse> getDonRealizados() throws ParseException {
        
        List<DonacionResponse> listResp=new ArrayList<>();
        listResp.addAll(getDonaciones("Realizado"));
        return listResp;
    }

    public List<DonacionResponse> getDonRealizados(UserRequest UserR) throws ParseException {  
        Usuario usuario1=usuarioRepository.findByCorreo(UserR.getCorreo()).orElse(null);    /// se obtiene al usuario  
        Donante don1=donanteRepository.findByIdusuario(usuario1.getIdUsuario()).orElse(null);  /// se obtiene los datos de un donante  

        List<DonacionResponse> listDonResp=new ArrayList<>();  

        listDonResp.addAll(getDonaciones(don1.getListDon(), "Realizado"));

        return listDonResp;
    }

    public List<DonacionResponse> getDNoRealizados() throws ParseException {
        List<DonacionResponse> listResp=new ArrayList<>();
        listResp.addAll(getDonaciones("Pendiente"));
        listResp.addAll(getDonaciones("SinResponsable"));
        
        return listResp;
    }

    public List<DonacionResponse> getDNoRealizados(UserRequest UserR) throws ParseException {
        Usuario usuario1=usuarioRepository.findByCorreo(UserR.getCorreo()).orElse(null);    /// se obtiene al usuario  
        Donante don1=donanteRepository.findByIdusuario(usuario1.getIdUsuario()).orElse(null);  /// se obtiene los datos de un donante  
        
        List<DonacionResponse> listDonResp=new ArrayList<>();  

        listDonResp.addAll(getDonaciones(don1.getListDon(), "Pendiente"));
        listDonResp.addAll(getDonaciones(don1.getListDon(), "SinResponsable"));

        return listDonResp;
    }
    
    // TODO Agregar getDonPendiente, getDonSinRepsentante para el voluntario repesentante y colaborador
    


    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public boolean realizarD(DonacionRequest request) {
        boolean salida=false;
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        Donante don1=donanteRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
        if(don1 != null){
            Donacion donacion1=Donacion.builder()
                        .cantidad(request.getCantidad())
                        .tipo_ap(request.getTipo_ap())
                        .fecha_hora_adquisicion(transformarFechaHora(request.getFechaHoraRecogida()))
                        .cantidadReqVol(0)
                        .nroVoluntariosC(0)
                        .voluntario(null)
                        .usuario(don1)
                        .listVoluntariosColab(new ArrayList<>())
                        .ubicacion(request.getUbicacion())
                        .build();
            donacionRepository.save(donacion1);

            Donacion donEval=donacionRepository.findByIddonacion(donacion1.getIddonacion()).orElse(null);
            if(donEval != null){
                salida=true;
            }
        }
        return salida;
    }

    private Calendar transformarFechaHora(String fechahora){  /// formato fecha hora: dd/MM/yy/hh/mm    formato fecha hora: dd/MM/yy hh:mm:ss  
        System.out.println("fechaHOra: "+fechahora);
        String[] FH=fechahora.split("/");
        SimpleDateFormat formatF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fech=FH[2]+"-" + FH[1] + "-" + FH[0];  /// dd/MM/yyyy => yyyy-MM-dd
        String hor=FH[3] + ":" +FH[4]+":00";   //// hh:mm:ss
        Calendar salida = new GregorianCalendar();
        Date salida1;
        try {
            salida1 = (Date) formatF.parse(fech+" "+hor);
            salida.setTime(salida1);
            return salida;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            salida1=new Date(0);
            salida.setTime(salida1);
            return salida;
        }

    }

    private Calendar transformarFechaHora2(String fechahora){  /// formato fecha hora: dd/MM/yy/hh/mm    formato fecha hora: dd/MM/yy hh:mm:ss  
        System.out.println("fechaHOra: "+fechahora);
        String[] FH=fechahora.split("/");
        SimpleDateFormat formatF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fech=FH[2]+"-" + FH[1] + "-" + FH[0];  /// dd/MM/yyyy => yyyy-MM-dd
        String hor="23:59:00";   //// hh:mm:ss
        Calendar salida = new GregorianCalendar();
        Date salida1;
        try {
            salida1 = (Date) formatF.parse(fech+" "+hor);
            salida.setTime(salida1);
            return salida;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            salida1=new Date(0);
            salida.setTime(salida1);
            return salida;
        }

    }



    @Transactional
    public boolean deleteDonacion(DeleteDonacionRequest request) {
        boolean salida=false;

        Donacion donacion1=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user != null){
            Donante don1=donanteRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
            if(donacion1 != null && don1 != null && don1.getListDon().contains(donacion1)){
                if(!verificarEstadoDonacion(donacion1).equalsIgnoreCase("Realizado")){
                    donacionRepository.delete(donacion1);
                    salida = true;
                    
                }
            }
        }

        return salida;
    }

    @Transactional
    public boolean updateUserDonacion(EditDonacionRequest request){

        boolean salida=false;
    
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user!=null){
            Donante don1=donanteRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
            Donacion donacion1=donacionRepository.findByIddonacion(request.getIdDonacion()).orElse(null);

            if( don1 != null && donacion1 != null && don1.getListDon().contains(donacion1) ){

                donacion1=editDon(donacion1,request);
                donacionRepository.save(donacion1);
                salida = true;
            }

        }
    
        return salida;
    }

    private Donacion editDon(Donacion donacion1, EditDonacionRequest request) {
        
        if(request.getCantidad() != 0 || request.getCantidad() != null){
            donacion1.setCantidad(request.getCantidad());
        }
        if(!request.getTipo_ap().equalsIgnoreCase("") || !request.getTipo_ap().equalsIgnoreCase(null)){
            donacion1.setTipo_ap(request.getTipo_ap());
        }
        if(!request.getUbicacion().equalsIgnoreCase("") || !request.getUbicacion().equalsIgnoreCase(null)){
            donacion1.setUbicacion(request.getUbicacion());
        }
        if(!request.getFechaHoraRecogida().equalsIgnoreCase("") || !request.getFechaHoraRecogida().equalsIgnoreCase(null)){
            Calendar p=transformarFechaHora(request.getFechaHoraRecogida());
            if(p!=null){
                donacion1.setFecha_hora_adquisicion(p);
            }
        }

        return donacion1;
    }
        
////////////////////////////////////////////////////////////////////// ALIMENTO Y PRODUCTO /////////////////////////////////////////////////
    @Transactional
    public boolean terminarDonacionAlimentos(List<AlimentoFinishResponse> listaAli){
        boolean salida=false;
        
        for(int i=0;i<listaAli.size();i++){

            Alimento ali=Alimento.builder()
                    .fechaVenc(transformarFechaHora2(listaAli.get(i).getFecha_Vencimiento()))
                    .cantidad(listaAli.get(i).getCantidad())
                    .tipo(listaAli.get(i).getTipo())
                    .nombre(listaAli.get(i).getNombreAlimento())
                    .estado(listaAli.get(i).getEstado())
                    .build();
            alimentoRepository.save(ali);
            Donacion don1=donacionRepository.findByIddonacion(listaAli.get(i).getIddonacion()).orElse(null);
            if(don1 != null){
                ContieneA contA=ContieneA.builder()
                            .alimento(ali)
                            .donacion(don1)
                            .cantidadA(listaAli.get(i).getCantidad())
                            .build();
                contieneARepository.save(contA);
                salida=true;
            }

        }

        return salida;
    }

    @Transactional
    public boolean terminarDonacionProductos(List<ProductoFinishResponse> listaAli){
        boolean salida=false;
        for(int i=0;i<listaAli.size();i++){

            Producto pro=Producto.builder()
                    .cantidad(listaAli.get(i).getCantidad())
                    .tipo(listaAli.get(i).getTipo())
                    .estado(listaAli.get(i).getEstado())
                    .build();
            productoRepository.save(pro);
            Donacion don1=donacionRepository.findByIddonacion(listaAli.get(i).getIddonacion()).orElse(null);
            if(don1 != null){
                ContieneP contP=ContieneP.builder()
                            .producto(pro)
                            .donacion(don1)
                            .cantidadP(listaAli.get(i).getCantidad())
                            .build();
                contienePRepository.save(contP);
                salida=true;
            }

        }

        return salida;
    }


}
