package Backend.BackendINF281.Inventario.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.Inventario.Services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="Inventario de productos(/productos)")
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class ProductoController {

    private final ProductoService productorService;

    @Operation(
        summary = "Se obtienen todos los productos del inventario.",
        description = ""
    )
    @GetMapping(value="getAllProductos")
    public List<ProductoResponse> getAllProductos() throws ParseException {
        return productorService.getAllProductos();
    }

}
