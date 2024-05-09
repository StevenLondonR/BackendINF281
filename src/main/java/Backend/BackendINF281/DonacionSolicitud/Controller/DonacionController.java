package Backend.BackendINF281.DonacionSolicitud.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.DonacionSolicitud.Services.DonacionService;
import Backend.BackendINF281.Inventario.Controller.AlimentoFinishResponse;
import Backend.BackendINF281.Inventario.Controller.ProductoFinishResponse;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name="Donacion_Controller(/donaciones)", description = "Operaciones como: - Obtener todas las donaciones, - obtener todas las donaciones realizadas, - obtener las donaciones realizadas por un usuario Donante, - obtener todas las donaciones no realizadas, - obtener las donaciones no realizadas por un usuario Donante, - obtener las donaciones en las que un usuario Voluntario es Responsable, - obtener las donaciones en las que un usuario Vountario es Colaborador, - un Usuario Donante puede realizar una donacion. ")
@RestController
@RequestMapping("/donaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class DonacionController {

    private final DonacionService donService; 

    @Operation(
        summary = "Obtener todas las donaciones", 
        description ="En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante"
    )
    @GetMapping(value="getAllDonaciones")
    public List<DonacionResponse> getAllDonaciones() throws ParseException {
        return donService.getAll();
    }
    
    @Operation(
        summary = "Obtener todas las donaciones realizadas",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante"
    )
    @GetMapping(value="getDonRealizadas")
    public List<DonacionResponse> getDonRealizadas() throws ParseException {
        return donService.getDonRealizados();
    }
    
    @Operation(
        summary = "Obtener las donaciones realizadas de un usuario donante",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante"
    )
    @PostMapping(value="getDonRealizadas")
    public List<DonacionResponse> getDonRealizadasU(@RequestBody UserRequest User) throws ParseException {
        return donService.getDonRealizados(User); 
    }

    @Operation(
        summary = "Obtener las donaciones no realizadas",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizad. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante"
    )
    @GetMapping(value="getDonNoRealizadas")
    public List<DonacionResponse> getDonNoRealizadasU() throws ParseException {
        return donService.getDNoRealizados();
    }

    @Operation(
        summary = "Obtener las donaciones no realizadas de un usuario donante",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante"
    )
    @PostMapping(value="getDonNoRealizadas")
    public List<DonacionResponse> getDonNoRealizadasU(@RequestBody UserRequest User) throws ParseException {
       return donService.getDNoRealizados(User);
    }
    // TODO Agregar getDonPendiente, getDonSinRepsentante para el voluntario repesentante y colaborador
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Operation(
        summary = "Realizar una donacion, accion que puede realizar un Usuario Donante",
        description = "En la entrada los atributos: correo de usuario donante y fechaHoraRecogida en el siguiente formato dd/MM/yyyy/HH/mm   =>  Esta accion modificara la tabla donacion  "
    )
    @PostMapping(value="realizarDonacion")
    public boolean realizarDonacion(@RequestBody DonacionRequest request) {
        return donService.realizarD(request);
    }
    
    @Operation(
        summary = "Usuario donante puede eliminar una donacion que no fue 'Realizada' ",
        description = "Esta accion modificara la tabla Donacion"
    )
    @PostMapping(value="deleteDonacion")
    public boolean deleteDonacion(@RequestBody DeleteDonacionRequest request){
        return donService.deleteDonacion(request);
    }

    @Operation(
        summary = "Usuario donante puede editar una donacion que no fue realizada",
        description = "En la entrada los atributos: correo de usuario donante y fechaHoraRecogida en el siguiente formato dd/MM/yyyy/HH/mm   =>  Esta accion modifica la tabla Donacion "
    )
    @PatchMapping(value="editarDonacion")
    public boolean editarDonacion(@RequestBody EditDonacionRequest request){
        return donService.updateUserDonacion(request);
    }


/////////////////////////////////////////////VOLUNTARIO///////////////////////
    @Operation(
        summary = "Obtener las donaciones que escogio un usuario Volutnario Responsable. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante.   ",
        description = "Importante: En la salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante.  En la salida se mostrara el correo del usuario Voluntario Responsable"
    )
    @PostMapping(value="getAllDonacionesResponsable")
    public List<DonacionResponse> getAllDonacionesResponsable(@RequestBody UserRequest User) throws ParseException {
        return donService.getDonacionesResponsable(User);
    }

    @Operation(
        summary = "Obtener las donaciones que escogio un usuario Volutnario Colaborador .  Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante.  ",
        description = "Importante: En la salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario donante. En la salida no se mostraran los datos del voluntario colaborador. "
    )
    @PostMapping(value="getAllDonacionesColaborador")
    public List<DonacionResponse> getAllDonacionesColaborador(@RequestBody UserRequest User) throws ParseException {
        return donService.getDonacionesColaborador(User);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Operation(
        summary = "Para concluir una Donacion y realizar un registro de los alimentos",
        description = "Al concluir una Donacion se registraran una lista de alimentos. Esta accion modifca las siguientes tablas: Alimento, ContieneA "
    )
    @PostMapping(value="terminarDonacionAlimentos")
    public boolean terminarDonacionAlimentos(@RequestBody List<AlimentoFinishResponse> listaAli) throws ParseException {
        return donService.terminarDonacionAlimentos(listaAli);
    }

    @Operation(
        summary = "Para terminar una Donacion y realizar un registro de los Productos",
        description = "Al concluir una Donacion se registraran una lista de Productos. Esta accion modifica las siguientes tablas: Producto y ContieneP "
    )
    @PostMapping(value="terminarDonacionProductos")
    public boolean terminarDonacionProductos(@RequestBody List<ProductoFinishResponse> listaAli) throws ParseException {
        return donService.terminarDonacionProductos(listaAli);
    }


}
