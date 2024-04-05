package Backend.BackendINF281.NuestraOrganizacion.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.NuestraOrganizacion.Service.OrgService;
import Backend.BackendINF281.NuestraOrganizacion.model.NuestraOrganizacion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/nuestraOrg")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class NuestraOrgController {
    
    private final OrgService orgService;

    @PostMapping(value="save")
    public ResponseEntity<Boolean> postMethodName(@RequestBody OrgSaveRequest orgRequest) {
        return ResponseEntity.ok(orgService.saveOrg(orgRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<NuestraOrganizacion> getMethodName(@PathVariable Integer id) {
        NuestraOrganizacion org=orgService.getDatos(id);
        if(org==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(org);
    }
    
    
    @PatchMapping()
    public ResponseEntity<Boolean> updateOrg(@RequestBody OrgRequest orgRequest){
        return ResponseEntity.ok(orgService.updateOrg(orgRequest));
    }

}
