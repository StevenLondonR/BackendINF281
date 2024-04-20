package Backend.BackendINF281.Inventario.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.Inventario.Services.AlimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="Solicitud_Controller(/solicitudes)")
@RestController
@RequestMapping("/alimentos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class AlimentoController {

    private final AlimentoService alimentoService;

    @Operation(
        summary = "Se obtienen todos los alimentos ",
        description = ""
    )
    @GetMapping(value="getAllAlimentos")
    public List<AlimentoResponse> getAllAlimentos() throws ParseException {
        return alimentoService.getAllAlimentos();
    }




    
}
