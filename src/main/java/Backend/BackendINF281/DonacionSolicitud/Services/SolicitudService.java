package Backend.BackendINF281.DonacionSolicitud.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.DonacionSolicitud.Controller.DeleteSolicitudRequest;
import Backend.BackendINF281.DonacionSolicitud.Controller.EditSolicitudRequest;
import Backend.BackendINF281.DonacionSolicitud.Controller.SolicitudRequest;
import Backend.BackendINF281.DonacionSolicitud.Controller.SolicitudResponse;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaA;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaAPK;
import Backend.BackendINF281.DonacionSolicitud.Models.SolicitaP;
import Backend.BackendINF281.DonacionSolicitud.Models.Solicitud;
import Backend.BackendINF281.DonacionSolicitud.Repository.SolicitaARepository;
import Backend.BackendINF281.DonacionSolicitud.Repository.SolicitaPRepository;
import Backend.BackendINF281.DonacionSolicitud.Repository.SolicitudRepository;
import Backend.BackendINF281.Inventario.Controller.AlimentoFinishResponse;
import Backend.BackendINF281.Inventario.Controller.AlimentoSolFinishResponse;
import Backend.BackendINF281.Inventario.Controller.ProductoFinishResponse;
import Backend.BackendINF281.Inventario.Controller.ProductoSolFinishResponse;
import Backend.BackendINF281.Inventario.Models.Alimento;
import Backend.BackendINF281.Inventario.Models.Producto;
import Backend.BackendINF281.Inventario.Repository.AlimentoRepository;
import Backend.BackendINF281.Inventario.Repository.ProductoRepository;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import Backend.BackendINF281.modulo_usuario.models.Receptor;
import Backend.BackendINF281.modulo_usuario.models.Usuario;
import Backend.BackendINF281.modulo_usuario.models.Voluntario;
import Backend.BackendINF281.modulo_usuario.repository.ReceptorRepository;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import Backend.BackendINF281.modulo_usuario.repository.VoluntarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReceptorRepository receptorRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final ProductoRepository productoRepository;
    private final AlimentoRepository alimentoRepository;
    private final SolicitaARepository solicitaARepository;
    private final SolicitaPRepository solicitaPRepository;

    public List<SolicitudResponse> getAllSolicitud() throws ParseException{
        
        List<Solicitud> listSol=solicitudRepository.findAll();
        List<SolicitudResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listSol.size();i++){
            Usuario user=usuarioRepository.findByIdUsuario(listSol.get(i).getUsuario().getIdusuario()).orElse(null);
            String respon="";
            Voluntario vol1 = listSol.get(i).getVoluntario() ;
            //System.out.println(vol1);
            if(vol1 != null){
                Usuario user2=usuarioRepository.findByIdUsuario(vol1.getIdvoluntario()).orElse(null);
                if(user2 != null){
                    respon=user2.getCorreo();
                }
            }
            Calendar cal=listSol.get(i).getFecha_hora_prog();
            Map<Integer,Integer> al=separarAli(listSol.get(i).getTipo_ap());
            Map<Integer,Integer> pr=separarProd(listSol.get(i).getTipo_ap());
            //System.out.println(al.keySet().size());
                
            SolicitudResponse SolResp=SolicitudResponse.builder()
                            .idSolicitud(listSol.get(i).getIdsolicitud())
                            .nombreU(user.getNombre())
                            .apellidoU(user.getApellido())
                            .telefonoU(user.getTelefono())
                            .cantidad(listSol.get(i).getCantidad())
                            .tipo_ap(obtenerAli(al)+";"+obtenerProd(pr))
                            .fechaHoraProg(convertGregorianDate(cal)) // convertido a la salida => dd/MM/yyyy HH:mm:ss 
                            .estado(verificarEstadoSolicitud(listSol.get(i)))
                            .correoResponsable(respon)
                            .ubicacion(listSol.get(i).getUbicacion())
                            .nroRequeridoCol(listSol.get(i).getCantidadReqVol())
                            .nroColaboradores(listSol.get(i).getNroVoluntariosC())
                            .build();
            listResponse.add(SolResp);
        }
        return listResponse;


    }

    private String verificarEstadoSolicitud(Solicitud solicitud){  // verifica el estado de una solicitud
        String salida="";    
        if(solicitud.getVoluntario() != null){
            salida="Pendiente";
        }else{
            salida="SinResponsable";
        }
        if(solicitud.getListRelacionAli().size() > 0 || solicitud.getListRelacionProd().size() > 0){
            salida="Realizado";
        }
        return salida;
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

    private List<SolicitudResponse> getSolicitud(String tipo) throws ParseException{  // lista por: SinResponsable,Pendiente, Realizado   
        List<Solicitud> listDon=solicitudRepository.findAll();
        List<SolicitudResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listDon.size();i++){ 
            if(verificarEstadoSolicitud(listDon.get(i)).equalsIgnoreCase(tipo) ){
                Usuario user=usuarioRepository.findByIdUsuario(listDon.get(i).getUsuario().getIdusuario()).orElse(null);
                Voluntario vol1=listDon.get(i).getVoluntario();
                String respon="";
                if(vol1 != null){
                    Usuario user2=usuarioRepository.findByIdUsuario(vol1.getIdvoluntario()).orElse(null);
                    
                    if(user2 != null){
                        respon=user2.getCorreo();
                    }
                }
                
                Map<Integer,Integer> al=separarAli(listDon.get(i).getTipo_ap());
                Map<Integer,Integer> pr=separarProd(listDon.get(i).getTipo_ap());
                //System.out.println(listDon.get(i));
                //System.out.println(pr.size());

                SolicitudResponse donResp=SolicitudResponse.builder()
                                .idSolicitud(listDon.get(i).getIdsolicitud())
                                .nombreU(user.getNombre())
                                .apellidoU(user.getApellido())
                                .telefonoU(user.getTelefono())
                                .cantidad(listDon.get(i).getCantidad())
                                .tipo_ap(obtenerAli(al)+";"+obtenerProd(pr))
                                .fechaHoraProg(convertGregorianDate(listDon.get(i).getFecha_hora_prog())) // TODO veficar la posicion de los datos al convertir a string 
                                .estado(verificarEstadoSolicitud(listDon.get(i)))
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
     
    public List<SolicitudResponse> getSolRealizados() throws ParseException {
        
        List<SolicitudResponse> listResp=new ArrayList<>();
        listResp.addAll(getSolicitud("Realizado"));
        return listResp; 

    } 

    public List<SolicitudResponse> getSolRealizados(UserRequest UserR) throws ParseException {  
        Usuario usuario1=usuarioRepository.findByCorreo(UserR.getCorreo()).orElse(null);    /// se obtiene al usuario  
        Receptor rec1=receptorRepository.findByIdusuario(usuario1.getIdUsuario()).orElse(null);  /// se obtiene los datos de un donante  

        List<SolicitudResponse> listRecResp=new ArrayList<>();  

        listRecResp.addAll(getSolicitud(rec1.getListSolicitudesR(), "Realizado"));

        return listRecResp;
    } 

    private List<SolicitudResponse> getSolicitud(List<Solicitud> listSolicitudes, String tipo) throws ParseException{
        List<Solicitud> listRec=new ArrayList<>();
        listRec.addAll(listSolicitudes);
        List<SolicitudResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listRec.size();i++){
            if(verificarEstadoSolicitud(listRec.get(i)).equalsIgnoreCase(tipo) || tipo.equalsIgnoreCase("ALL")){
                Usuario user=usuarioRepository.findByIdUsuario(listRec.get(i).getUsuario().getIdusuario()).orElse(null);
                Voluntario vol1=listRec.get(i).getVoluntario();
                String respon="";
                if(vol1 != null){
                    Usuario user2=usuarioRepository.findByIdUsuario(vol1.getIdvoluntario()).orElse(null);
                    if(user2 != null){
                        respon=user2.getCorreo();
                    }
                }

                SolicitudResponse donResp=SolicitudResponse.builder()
                                .idSolicitud(listRec.get(i).getIdsolicitud())
                                .nombreU(user.getNombre())
                                .apellidoU(user.getApellido())
                                .telefonoU(user.getTelefono())
                                .cantidad(listRec.get(i).getCantidad())
                                .tipo_ap(listRec.get(i).getTipo_ap())
                                .fechaHoraProg(convertGregorianDate(listRec.get(i).getFecha_hora_prog())) // TODO veficar la posicion de los datos al convertir a string 
                                .estado(verificarEstadoSolicitud(listRec.get(i)))
                                .correoResponsable(respon)
                                .ubicacion(listRec.get(i).getUbicacion())
                                .nroRequeridoCol(listRec.get(i).getCantidadReqVol())
                                .nroColaboradores(listRec.get(i).getNroVoluntariosC())
                                .build();
                listResponse.add(donResp);
            }
        }
        return listResponse;
    } 

    public List<SolicitudResponse> getSolicitudResponsable(UserRequest request) throws ParseException{

        List<SolicitudResponse> listResponse=new ArrayList<>();
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        Voluntario vol1=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        if(vol1 != null){

            List<Solicitud> listDonR=vol1.getListSolicitudesVolR();
            listResponse.addAll(getSolicitud(listDonR,"ALL"));

        }

        return listResponse;
    }

    public List<SolicitudResponse> getSolicitudesColaborador(UserRequest request) throws ParseException{

        List<SolicitudResponse> listResponse=new ArrayList<>();
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        Voluntario vol1=voluntarioRepository.findByIdvoluntario(user.getIdUsuario()).orElse(null);

        if(vol1 != null){

            List<Solicitud> listDonC=vol1.getListSolicitudesVolV();
            listResponse.addAll(getSolicitud(listDonC,"ALL"));

        }

        return listResponse;
    }





   

   
    public List<SolicitudResponse> getSNoRealizados() throws ParseException {
        List<SolicitudResponse> listResp=new ArrayList<>();
        listResp.addAll(getSolicitud("Pendiente"));
        listResp.addAll(getSolicitud("SinResponsable"));
        
        return listResp;
    }

    public List<SolicitudResponse> getSNoRealizados(UserRequest UserR) throws ParseException {
        Usuario usuario1=usuarioRepository.findByCorreo(UserR.getCorreo()).orElse(null);    /// se obtiene al usuario  
        Receptor rec1=receptorRepository.findByIdusuario(usuario1.getIdUsuario()).orElse(null);  /// se obtiene los datos de un donante  
        
        List<SolicitudResponse> listRecResp=new ArrayList<>();  

        listRecResp.addAll(getSolicitud(rec1.getListSolicitudesR(), "Pendiente"));
        listRecResp.addAll(getSolicitud(rec1.getListSolicitudesR(), "SinResponsable"));

        return listRecResp;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean realizarSolicitud(SolicitudRequest request) {
        boolean salida=false;
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        Receptor rec1=receptorRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
        if(rec1 != null){
            Map<Integer,Integer> al=separarAli(request.getTipo_ap());
            Map<Integer,Integer> pr=separarProd(request.getTipo_ap());

            Solicitud solicitud1=Solicitud.builder()
                        .cantidad(request.getCantidad())
                        .tipo_ap(request.getTipo_ap())
                        .fecha_hora_prog(transformarFechaHora(request.getFechaHoraProgramada()))
                        .cantidadReqVol(0)
                        .nroVoluntariosC(0)
                        .voluntario(null)
                        .usuario(rec1)
                        .ubicacion(request.getUbicacion())
                        .listVoluntariosColab(new ArrayList<>())
                        .build();
            solicitudRepository.save(solicitud1);

            Solicitud SolEval=solicitudRepository.findByIdsolicitud(solicitud1.getIdsolicitud()).orElse(null);
            if(SolEval != null){
                Iterator a = al.keySet().iterator();
                Iterator p = pr.keySet().iterator();
                while(a.hasNext()){
                    int keyA=(int) a.next();
                    System.out.println("key: "+keyA);
                    Alimento alimentoM=alimentoRepository.findByIdalimento(keyA).orElse(null);
                    if(alimentoM!=null){
                        int cat=alimentoM.getCantidad();
                        int res=cat-al.get(keyA);
                        alimentoM.setCantidad(res);
                        alimentoRepository.save(alimentoM);
                    } 
                }

                while(p.hasNext()){
                    int keyA=(int) p.next();
                    System.out.println("Key: "+keyA);
                    Producto productoM=productoRepository.findByIdproducto(keyA).orElse(null);
                    if(productoM!=null){
                        int cat=productoM.getCantidad();
                        int res=cat-pr.get(keyA);
                        productoM.setCantidad(res);
                        productoRepository.save(productoM);
                    }
                }


                salida=true;
            }
        }
        return salida;
    }

    private Map<Integer,Integer> separarAli(String entrada){
        System.out.println("separarAli: "+entrada);
        Map<Integer,Integer> salida=new HashMap<>();
        
        String[] sepAll=entrada.split(";");
        System.out.println("sepAll: "+sepAll.length);
        String[] sepAli=sepAll[0].split(",");
        System.out.println("sepAli: "+sepAli[0]);
        for(int i =0;i<sepAli.length;i++){
            
                String[] p=sepAli[i].split("\\.");
            if(p.length>1){
                if(p.length>0){
                    salida.put( Integer.parseInt(p[1]), Integer.parseInt(p[0]));
                }
            }

        } 


        return salida;
    }

    private Map<Integer,Integer> separarProd(String entrada){
        System.out.println("separarProd: "+entrada);
        Map<Integer,Integer> salida=new HashMap<>();
        
        String[] sepAll=entrada.split(";");
        if(sepAll.length > 1){
            String[] sepAli=sepAll[1].split(",");
            for(int i =0;i<sepAli.length;i++){
                String[] p=sepAli[i].split("\\.");
                salida.put(Integer.parseInt(p[1]),Integer.parseInt(p[0]));
            }             
        }

        return salida;
    }

    private String obtenerAli( Map<Integer,Integer> entrada ){
        String salida="";   ////// salida[0][*]= nombre alimento , salida[1][*]=cantidad de alimento
        Iterator keys=entrada.keySet().iterator();
        int cont=0;
        while(keys.hasNext()){
            Integer key=(Integer)keys.next();
            
            Alimento a=alimentoRepository.findByIdalimento(key).orElse(null);
            System.out.println(a.getNombre());
            if(a!=null && cont==0){
                salida=entrada.get(key).toString()+a.getNombre();
            }else if(a!=null && cont>0){    
                salida=salida+","+entrada.get(key).toString()+a.getNombre();
            }
            cont++;
        }

        return salida;
    }


    private String obtenerProd( Map<Integer,Integer> entrada ){
        String salida="";   ////// salida[0][*]= nombre alimento , salida[1][*]=cantidad de alimento
        Iterator keys=entrada.keySet().iterator();
        int cont=0;
        while(keys.hasNext()){
            Integer key=(Integer)keys.next();
            
            Producto a=productoRepository.findByIdproducto(key).orElse(null);

            if(a!=null && cont == 0){
                salida=entrada.get(key).toString()+a.getTipo();
            }else if(a!=null && cont>0){
                salida=salida+","+entrada.get(key).toString()+a.getTipo();

            }

            cont++;
        }

        return salida;
    }


    private Calendar transformarFechaHora(String fechahora){  /// formato fecha hora: dd/MM/yy/hh/mm    formato fecha hora: dd/MM/yy hh:mm:ss  
        String[] FH=fechahora.split("/");
        if(FH.length>1){

            SimpleDateFormat formatF=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
        }else{
            return null;
        }
    }

    @Transactional
    public boolean updateUserSolicitud(EditSolicitudRequest request){

        boolean salida=false;
    
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user!=null){
            Receptor rec1=receptorRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
            Solicitud solicitud1=solicitudRepository.findByIdsolicitud(request.getIdSolicitud()).orElse(null);

            if( rec1 != null && solicitud1 != null && rec1.getListSolicitudesR().contains(solicitud1) ){

                solicitud1=editSol(solicitud1,request);
                solicitudRepository.save(solicitud1);
                salida = true;
            }

        }
    
        return salida;
    }

    private Solicitud editSol(Solicitud solicitud1, EditSolicitudRequest request) {
        
        if(request.getCantidad() != 0 || request.getCantidad() != null){
            solicitud1.setCantidad(request.getCantidad());
        }
        if(!request.getTipo_ap().equalsIgnoreCase("") || !request.getTipo_ap().equalsIgnoreCase(null)){
            solicitud1.setTipo_ap(request.getTipo_ap());
        }
        if(!request.getUbicacion().equalsIgnoreCase("") || !request.getUbicacion().equalsIgnoreCase(null)){
            solicitud1.setUbicacion(request.getUbicacion());
        }
        if(!request.getFechaHoraProgramada().equalsIgnoreCase("") || !request.getFechaHoraProgramada().equalsIgnoreCase(null)){
            Calendar p=transformarFechaHora(request.getFechaHoraProgramada());
            if(p!=null){
                solicitud1.setFecha_hora_prog(p);
            }
        }

        return solicitud1;
    }

    @Transactional
    public boolean deleteSolicitud(DeleteSolicitudRequest request) {
        boolean salida=false;

        Solicitud solicitud1=solicitudRepository.findByIdsolicitud(request.getIdSolicitud()).orElse(null);
        Usuario user=usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        if(user != null){
            Receptor rec1=receptorRepository.findByIdusuario(user.getIdUsuario()).orElse(null);
            if(solicitud1 != null && rec1 != null && rec1.getListSolicitudesR().contains(solicitud1)){
                
                if(!verificarEstadoSolicitud(solicitud1).equalsIgnoreCase("Realizado")){
                    Map<Integer,Integer> al=separarAli(solicitud1.getTipo_ap());
                    Map<Integer,Integer> pr=separarProd(solicitud1.getTipo_ap());

                    Iterator a = al.keySet().iterator();
                    Iterator p = pr.keySet().iterator();
                    while(a.hasNext()){
                        int keyA=(int) a.next();
                        Alimento alimentoM=alimentoRepository.findByIdalimento(keyA).orElse(null);
                        if(alimentoM!=null){
                            int cat=alimentoM.getCantidad();
                            int res=cat+al.get(keyA);
                            alimentoM.setCantidad(res);
                            alimentoRepository.save(alimentoM);
                        }
                    }

                    while(p.hasNext()){
                        int keyA=(int) p.next();
                        Producto productoM=productoRepository.findByIdproducto(keyA).orElse(null);
                        if(productoM!=null){
                            int cat=productoM.getCantidad();
                            int res=cat+al.get(keyA);
                            productoM.setCantidad(res);
                            productoRepository.save(productoM);
                        }
                    }

                    solicitudRepository.delete(solicitud1);


                    salida=true;
                }
            }
        }

        return salida;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public boolean concluirSolicitud(Integer IdSol){
    boolean salida=false;
    List<AlimentoSolFinishResponse> lA=new ArrayList<>();
    List<ProductoSolFinishResponse> lP=new ArrayList<>(); 
    Solicitud solA=solicitudRepository.findByIdsolicitud(IdSol).orElse(null);
    if(solA!=null){
        
        Map<Integer,Integer> a=separarAli(solA.getTipo_ap());
        Map<Integer,Integer> p=separarProd(solA.getTipo_ap());


        Iterator keys=a.keySet().iterator();
        Iterator keysP=p.keySet().iterator();
        //System.out.println(p.size());
        while(keys.hasNext()){
            Integer key=(Integer)keys.next();
            AlimentoSolFinishResponse res=AlimentoSolFinishResponse.builder()
                        .idsolitud(IdSol)
                        .idAlimento(key)
                        .cantidad(a.get(key))
                        .build();
            lA.add(res);
        }
        if (!lA.isEmpty()) {
            terminarSolicitudAlimentos(lA);
            salida = true;
        }
        while(keysP.hasNext()){
            Integer keyp=(Integer)keysP.next();
            ProductoSolFinishResponse res=ProductoSolFinishResponse.builder()
                        .idsolitud(IdSol)
                        .idProducto(keyp)
                        .cantidad(p.get(keyp))
                        .build();
            lP.add(res);

        }
        if (!lP.isEmpty()) {
            terminarSolicitudProductos(lP);
            salida = true;
        }

    }

    return salida;
}   




@Transactional
    public boolean terminarSolicitudAlimentos(List<AlimentoSolFinishResponse> listaAli){
        boolean salida=false;
        for(int i=0;i<listaAli.size();i++){
            AlimentoSolFinishResponse solF=listaAli.get(i);
            Alimento ali=alimentoRepository.findByIdalimento(solF.getIdAlimento()).orElse(null);
            if(ali != null){
                int cantA=ali.getCantidad();
                if(cantA>-1){
                    ali.setCantidad(cantA);
                    alimentoRepository.save(ali);
                    Solicitud sol1=solicitudRepository.findByIdsolicitud(solF.getIdsolitud()).orElse(null);
                    if(sol1 != null){
                        SolicitaA contA=SolicitaA.builder()
                                    .alimento(ali)
                                    .solicitud(sol1)
                                    .cantidadA(listaAli.get(i).getCantidad())
                                    .build();
                        solicitaARepository.save(contA);
                        salida=true;
                    }
                }
    
    
            }

        }
                

        return salida;
    }


    @Transactional
    public boolean terminarSolicitudProductos(List<ProductoSolFinishResponse> listaProd){
        boolean salida=false;
        for(int i=0;i<listaProd.size();i++){
            ProductoSolFinishResponse solF=listaProd.get(i);
            Producto prod=productoRepository.findByIdproducto(solF.getIdProducto()).orElse(null);
            if(prod != null){
                int cantP=prod.getCantidad();
                if(cantP>-1){
                    prod.setCantidad(cantP);
                    productoRepository.save(prod);
                    Solicitud sol1=solicitudRepository.findByIdsolicitud(solF.getIdsolitud()).orElse(null);
                    if(sol1 != null){
                        SolicitaP contP=SolicitaP.builder()
                                    .producto(prod)
                                    .solicitud(sol1)
                                    .cantidadP(listaProd.get(i).getCantidad())
                                    .build();
                        solicitaPRepository.save(contP);
                        salida=true;
                    }
                }
    
    
            }

        }
                

        return salida;
    }


}




