package Backend.BackendINF281.NuestraOrganizacion.Controller;


import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrgResponse {
    private String mision;

    private String vision;

    private String que_hacemos;


}
