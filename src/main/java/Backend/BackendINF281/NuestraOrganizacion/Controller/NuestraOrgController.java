package Backend.BackendINF281.NuestraOrganizacion.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Backend.BackendINF281.NuestraOrganizacion.Service.OrgService;
import Backend.BackendINF281.NuestraOrganizacion.model.NuestraOrganizacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name="Nuestra_Organizacion_Controller(/nuestraOrg)", description = "Operaciones como: Obtener los datos de nuestra organizacion, - Guardar los datos de nuestra organizacion, - Actualizar los datos de nuestra organizacion  ")
@RestController
@RequestMapping("/nuestraOrg")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class NuestraOrgController {
    
    
    
    private final OrgService orgService;

    @Operation(
        summary = "Obtener los datos de nuestra organizacion",
        description = "Modifica la tabla datos_organizacion"
    )
    @PostMapping(value="save")
    public ResponseEntity<Boolean> saveOrg(@RequestBody OrgSaveRequest orgRequest) throws IOException {
        return ResponseEntity.ok(orgService.saveOrg(orgRequest));
    }


    @Operation(
        summary = "Para cargar una imagen para la 'mision' de nuestra organizacion",
        description = "Modifica la tabla datos_organizacion"
    )
    @PostMapping(value="uploadImageMision")
    public Boolean uploadImageMision(@RequestParam("imagen") MultipartFile imagen,@RequestParam("idNuestraOrg") Integer idNuestraOrg) throws IOException {
        return orgService.uploadImagenMision(imagen,idNuestraOrg);
    }

    @Operation(
        summary = "Para cargar una imagen para la 'mision' de nuestra organizacion",
        description = "Modifica la tabla datos_organizacion"
    )
    @PostMapping(value="uploadImageVision")
    public Boolean uploadImageVision(@RequestParam("imagen") MultipartFile imagen,@RequestParam("idNuestraOrg") Integer idNuestraOrg) throws IOException {
        return orgService.uploadImagenVision(imagen,idNuestraOrg);
    }

    @Operation(
        summary = "Para cargar una imagen para la 'mision' de nuestra organizacion",
        description = "Modifica la tabla datos_organizacion"
    )
    @PostMapping(value="uploadImageQueHacemos")
    public Boolean uploadImageQueHacemos(@RequestParam("imagen") MultipartFile imagen,@RequestParam("idNuestraOrg") Integer idNuestraOrg) throws IOException {
        return orgService.uploadImagenQueHacemos(imagen,idNuestraOrg);
    }

    @Operation(
        summary = "Para cargar una imagen para la 'mision' de nuestra organizacion",
        description = "Modifica la tabla datos_organizacion"
    )
    @PostMapping(value="uploadImageExtra")
    public Boolean uploadImageExtra(@RequestParam("imagen") MultipartFile imagen, @RequestParam("idNuestraOrg") Integer idNuestraOrg) throws IOException {
        return orgService.uploadImagenExtra(imagen,idNuestraOrg);
    }

    
    @Operation(
        summary = " Se optienen las imagenes de 'Mision' de Nuestra organizacion ",
        description = "Devolvera una lista de rutas de las imagenes"
    )
    @GetMapping("getImagenesMision/{id}")
    public ResponseEntity<List<String>> getImagenesMision(@PathVariable Integer id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(orgService.getImagenesMision(id));
    }

    @Operation(
        summary = " Se optienen las imagenes de 'Vision' de Nuestra organizacion ",
        description = "Devolvera una lista de rutas de las imagenes"
    )
    @GetMapping("getImagenesVision/{id}")
    public List<String> getImagenesVision(@PathVariable Integer id) throws IOException {
        return orgService.getImagenesVision(id);
    }


    @Operation(
        summary = " Se optienen las imagenes de 'Que hacemos' de Nuestra organizacion ",
        description = "Devolvera una lista de rutas de las imagenes"
    )
    @GetMapping("getImagenesQueHacemos/{id}")
    public List<String> getImagenesQueHacemos(@PathVariable Integer id) throws IOException {
        return orgService.getImagenesQueHacemos(id);
    }


    @Operation(
        summary = " Se optienen las imagenes extras que es de Nuestra organizacion pero que no pertenecen a : 'Mision', 'Vision' y 'Que Hacemos'   ",
        description = "Devolvera una lista de rutas de las imagenes"
    )
    @GetMapping("getImagenesExtras/{id}")
    public List<String> getImagenesExtras(@PathVariable Integer id) throws IOException {
        return orgService.getImagenesExtras(id);
    }



    @Operation(
        summary = "Se obtienen los datos de la organizacion",
        description = ""
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrgResponse> getDatos(@PathVariable Integer id) {
        OrgResponse org=orgService.getDatos(id);
        if(org==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(org);
    }
    
    @Operation(
        summary = "Actualizar los datos de nuestra organizacion",
        description = "Modifica la tabla datos_organizacion"
    )
    @PatchMapping()
    public ResponseEntity<Boolean> updateOrg(@RequestBody OrgRequest orgRequest){
        return ResponseEntity.ok(orgService.updateOrg(orgRequest));
    }

}
