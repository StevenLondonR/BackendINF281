package Backend.BackendINF281.NuestraOrganizacion.Service;

import org.springframework.stereotype.Service;

import Backend.BackendINF281.NuestraOrganizacion.Controller.OrgRequest;
import Backend.BackendINF281.NuestraOrganizacion.Controller.OrgSaveRequest;
import Backend.BackendINF281.NuestraOrganizacion.Repository.NuestraOrgRepository;
import Backend.BackendINF281.NuestraOrganizacion.model.NuestraOrganizacion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrgService {

    private final NuestraOrgRepository nuestraOrgR;

    public boolean saveOrg( OrgSaveRequest orgRequest){

        NuestraOrganizacion org= NuestraOrganizacion.builder()
                .mision(orgRequest.getMision())
                .vision(orgRequest.getVision())
                .quehacemos(orgRequest.getQue_hacemos())    
                .build();
        nuestraOrgR.save(org);
        return true;
    }
    public NuestraOrganizacion getDatos(Integer id){

        NuestraOrganizacion org=nuestraOrgR.findByIdorganizacion(id).orElse(null);

        if(org!=null){
            return org;
        }
        return null;

    }

    @Transactional
    public boolean updateOrg( OrgRequest org){
        nuestraOrgR.updateOrg(org.getId(),org.getMision(),org.getVision(), org.getQue_hacemos());
        return true;
    }

}
