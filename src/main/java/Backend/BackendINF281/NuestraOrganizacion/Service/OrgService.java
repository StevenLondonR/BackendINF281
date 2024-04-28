package Backend.BackendINF281.NuestraOrganizacion.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Backend.BackendINF281.NuestraOrganizacion.Controller.ImageMisionRequest;
import Backend.BackendINF281.NuestraOrganizacion.Controller.OrgRequest;
import Backend.BackendINF281.NuestraOrganizacion.Controller.OrgResponse;
import Backend.BackendINF281.NuestraOrganizacion.Controller.OrgSaveRequest;
import Backend.BackendINF281.NuestraOrganizacion.Repository.NuestraOrgRepository;
import Backend.BackendINF281.NuestraOrganizacion.model.NuestraOrganizacion;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrgService {

    @Value("${media.location}")
    private String medialocation;

    private Path privateLoc;

    private final NuestraOrgRepository nuestraOrgR;

    private final HttpServletRequest request;



    @PostConstruct
    public void init() throws IOException{

        privateLoc=Paths.get(medialocation);
        Files.createDirectories(privateLoc);
    
    }


    public boolean saveOrg( OrgSaveRequest orgRequest) throws IOException{

        NuestraOrganizacion org= NuestraOrganizacion.builder()
            .mision(orgRequest.getMision())
            .vision(orgRequest.getVision())
            .quehacemos(orgRequest.getQue_hacemos())
            .imagenes(" ; ; ; ")    
            .build();
        nuestraOrgR.save(org);
        
        return true;
    }
    

    public boolean uploadImagenMision(MultipartFile imagenesMision, int idOrg) throws IOException{
        boolean salida=false;
        //System.out.println("-------------------------------------------------------------------------------");

        if(imagenesMision != null){
            //System.out.println("-------------------------------------------------------------------------------");

            NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
            
            //System.out.println("-------------------------------------------------------------------------------");
            if(nuestraO != null){
                //System.out.println("-------------------------------------------------------------------------------");

                String imgNameM=imagenesMision.getOriginalFilename();
                //System.out.println("nonbre: "+imgNameM);
                Path destinationImg=privateLoc.resolve(Paths.get(imgNameM))
                        .normalize().toAbsolutePath();
                //System.out.println("Path: "+destinationImg);
                try(InputStream input = imagenesMision.getInputStream()){
                    try {
                        //System.out.println("////////////////////////////////////////////-------------------------------------------------------------------------------");

                        Files.copy(input, destinationImg, StandardCopyOption.REPLACE_EXISTING);
                        //System.out.println("////////////////////////////////////////////-------------------------------------------------------------------------------");
                        String[] a=nuestraO.getImagenes().split(";");
                        //System.out.println("length A: "+a.length);
                        String[] c=a[0].split(",");
                        //System.out.println("length C: "+c.length);
                        if(c.length==0 || c[0].equals(" ")){

                            a[0]=imgNameM;
                        }else{
                            a[0]=a[0]+","+imgNameM;
                        }

                        String sa=a[0];
                        for(int e=1;e<a.length;e++){
                            sa=sa+";"+a[e];
                        }
                        nuestraO.setImagenes(sa);
                        nuestraOrgR.save(nuestraO);
                        salida=true;

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        return false;
                    }
                }
            }
            

        }

        return salida;
    }
 
    public boolean uploadImagenVision(MultipartFile imagenesVision, int idOrg) throws IOException{
        boolean salida=false;
        if(imagenesVision != null){
            NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
            if(nuestraO != null){
                String imgNameM=imagenesVision.getOriginalFilename();
                Path destinationImg=privateLoc.resolve(Paths.get(imgNameM))
                        .normalize().toAbsolutePath();
                try(InputStream input = imagenesVision.getInputStream()){
                    try {
                        Files.copy(input, destinationImg, StandardCopyOption.REPLACE_EXISTING);
                        String[] a=nuestraO.getImagenes().split(";");
                        String[] c=a[1].split(",");
                        if(c.length==0 || c[0].equals(" ")){
                            a[1]=imgNameM;
                        }else{
                            a[1]=","+imgNameM;
                        }
                        String sa=a[0];
                        for(int e=1;e<a.length;e++){
                            sa=sa+";"+a[e];
                        }
                        nuestraO.setImagenes(sa);
                        nuestraOrgR.save(nuestraO);
                        salida=true;
                        
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        return false;
                    }
                }
            }
            

        }

        return salida;
    }

    public boolean uploadImagenQueHacemos(MultipartFile imagenesQueHacemos, int idOrg) throws IOException{
        boolean salida=false;
        if(imagenesQueHacemos != null){
            NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
            if(nuestraO != null){
                String imgNameM=imagenesQueHacemos.getOriginalFilename();
                Path destinationImg=privateLoc.resolve(Paths.get(imgNameM))
                        .normalize().toAbsolutePath();
                try(InputStream input = imagenesQueHacemos.getInputStream()){
                    try {
                        Files.copy(input, destinationImg, StandardCopyOption.REPLACE_EXISTING);
                        String[] a=nuestraO.getImagenes().split(";");
                        String[] c=a[2].split(",");
                        if(c.length==0 || c[0].equals("")){
                            a[2]=imgNameM;
                        }else{
                            a[2]=","+imgNameM;
                        }
                        String sa=a[0];
                        for(int e=1;e<a.length;e++){
                            sa=sa+";"+a[e];
                        }
                        nuestraO.setImagenes(sa);
                        nuestraOrgR.save(nuestraO);
                        salida=true;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        return false;
                    }
                }
            }
            

        }

        return salida;
    }

    public boolean uploadImagenExtra(MultipartFile imagenesExtras, int idOrg) throws IOException{
        boolean salida=false;
        if(imagenesExtras != null){
            NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
            if(nuestraO != null){
                String imgNameM=imagenesExtras.getOriginalFilename();
                Path destinationImg=privateLoc.resolve(Paths.get(imgNameM))
                        .normalize().toAbsolutePath();
                try(InputStream input = imagenesExtras.getInputStream()){
                    try {
                        Files.copy(input, destinationImg, StandardCopyOption.REPLACE_EXISTING);
                        String[] a=nuestraO.getImagenes().split(";");
                        String[] c=a[3].split(",");
                        if(c.length==0 || c[0].equals(" ")){
                            a[3]=imgNameM;
                        }else{
                            a[3]=","+imgNameM;
                        }
                        String sa=a[0];
                        for(int e=1;e<a.length;e++){
                            sa=sa+";"+a[e];
                        }
                        nuestraO.setImagenes(sa);
                        nuestraOrgR.save(nuestraO);
                        salida=true;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        return false;
                    }
                }
            }
            

        }

        return salida;
    }

    public List<String> getImagenesMision(int idOrg) throws IOException{
        
        List<String> salida=new ArrayList<>();

        NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
        if(nuestraO != null){
            String[] tip=nuestraO.getImagenes().split(";");
            String[] ima=tip[0].split(",");
            for(int i=0;i<ima.length;i++){
                System.out.println("Archivos: "+ima[i]);
                Resource r=loadimage(ima[i]);
                // String host=request.getRequestURL().toString().replace(request.getRequestURI(), "");
                // String url=ServletUriComponentsBuilder
                //             .fromHttpUrl(host)
                //             .path("/imagenes/")
                //             .path(ima[i])
                //             .toUriString();
                // String host = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                // String url = host + "/nuestraOrg/" + ima[i];
                salida.add(r.getURI().toString());
            }
        }
        return salida;
    }

    public List<String> getImagenesVision(int idOrg) throws IOException{
        
        List<String> salida=new ArrayList<>();

        NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
        if(nuestraO != null){
            String[] tip=nuestraO.getImagenes().split(";");
            String[] ima=tip[1].split(",");
            for(String a: ima){
                Resource r=loadimage(a);
                salida.add(r.getURI().toString());
            }
        }
        return salida;
    }

    public List<String> getImagenesQueHacemos(int idOrg) throws IOException{
        
        List<String> salida=new ArrayList<>();

        NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
        if(nuestraO != null){
            String[] tip=nuestraO.getImagenes().split(";");
            String[] ima=tip[2].split(",");
            for(String a: ima){
                Resource r=loadimage(a);
                salida.add(r.getURI().toString());
            }
        }
        return salida;
    }

    public List<String> getImagenesExtras(int idOrg) throws IOException{
        
        List<String> salida=new ArrayList<>();

        NuestraOrganizacion nuestraO=nuestraOrgR.findByIdorganizacion(idOrg).orElse(null);
        if(nuestraO != null){
            String[] tip=nuestraO.getImagenes().split(";");
            String[] ima=tip[3].split(",");
            for(String a: ima){
                Resource r=loadimage(a);
                salida.add(r.getURI().toString());
            }
        }
        return salida;
    }


    public OrgResponse getDatos(Integer id){

        NuestraOrganizacion org=nuestraOrgR.findByIdorganizacion(id).orElse(null);

        OrgResponse orgR=OrgResponse.builder()
                    .mision(org.getMision())
                    .vision(org.getVision())
                    .que_hacemos(org.getQuehacemos())
                    .build();
        if(orgR!=null){
            return orgR;
        }
        return null;

    }

    @Transactional
    public boolean updateOrg( OrgRequest org){
        
        nuestraOrgR.updateOrg(org.getId(),org.getMision(),org.getVision(), org.getQue_hacemos());
        return true;

    }

    public Resource loadimage(String name) throws IOException{
        Path img=privateLoc.resolve(name);
        System.out.println("PATH: "+img.toUri());
        try {
            Resource resource=new UrlResource((img.toUri()));
            System.out.println("URL: "+resource.getURL());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                return null;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
