package Backend.BackendINF281.NuestraOrganizacion.Controller;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrgRequest {

    private Integer id;

    private String mision;

    private String vision;

    private String que_hacemos;

}
