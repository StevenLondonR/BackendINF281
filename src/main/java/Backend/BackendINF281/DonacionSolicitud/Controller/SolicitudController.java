package Backend.BackendINF281.DonacionSolicitud.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.DonacionSolicitud.Services.SolicitudService;
import Backend.BackendINF281.Inventario.Controller.AlimentoFinishResponse;
import Backend.BackendINF281.Inventario.Controller.AlimentoSolFinishResponse;
import Backend.BackendINF281.Inventario.Controller.ProductoFinishResponse;
import Backend.BackendINF281.Inventario.Controller.ProductoSolFinishResponse;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="Solicitud_Controller(/solicitudes)")
@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Operation(
        summary = "Obtener todas las solicitudes", 
        description ="En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor"
    )
    @GetMapping(value="getAllSolicitud")
    public List<SolicitudResponse> getAllSolicitud() throws ParseException {
        return solicitudService.getAllSolicitud();
    }

    
    @Operation(
        summary = "Obtener todas las donaciones realizadas",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor"
    )
    @GetMapping(value="getSolRealizados")
    public List<SolicitudResponse> getSolRealizados() throws ParseException {
        return solicitudService.getSolRealizados();
    }



    @Operation(
        summary = "Obtener las Solicitudes realizadas de un usuario Receptor",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor"
    )
    @PostMapping(value="getSolRealizados")
    public List<SolicitudResponse> getSolRealizadosU(@RequestBody UserRequest User) throws ParseException {
        return solicitudService.getSolRealizados(User); 
    }


    @Operation(
        summary = "Obtener las Solicitudes no realizadas",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor"
    )
    @GetMapping(value="getSolNoRealizados")
    public List<SolicitudResponse> getSolNoRealizados() throws ParseException {
        return solicitudService.getSNoRealizados();
    }


    @Operation(
        summary = "Obtener las Solicitudes no realizadas de un usuario Receptor",
        description = "En salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor"
    )
    @PostMapping(value="getSolNoRealizados")
    public List<SolicitudResponse> getSolNoRealizadosU(@RequestBody UserRequest User) throws ParseException {
        return solicitudService.getSNoRealizados(User);
    }
    // TODO Agregar getDonPendiente, getDonSinRepsentante para el voluntario repesentante y colaborador
    

    @Operation(
        summary = "Realizar una Solicitud, accion que puede realizar un Usuario Receptor",
        description = "En la entrada los atributos: correo de usuario Receptor y fechaHoraProgramada en el siguiente formato dd/MM/yyyy/HH/mm : El formato para tipo_ap es el siguiente: cantidad.idAliemento,cantidad.idAlimento;cantidad.idProducto,cantidad.idProducto"
    )
    @PostMapping(value="realizarSolicitud")
    public boolean realizarSolicitud(@RequestBody SolicitudRequest request) {
        return solicitudService.realizarSolicitud(request);
    }


    @Operation(
        summary = "Usuario Receptor puede eliminar una Solicitud que no fue 'Realizada'",
        description = ""
    )
    @PostMapping(value="deleteSolicitud")
    public boolean deleteSolicitud(@RequestBody DeleteSolicitudRequest request){
        return solicitudService.deleteSolicitud(request);
    }

    @Operation(
        summary = "Usuario Receptor puede editar una Solicitud que no fue 'Realizada'",
        description = "En la entrada los atributos: correo de usuario Receptor y fechaHoraRecogida en el siguiente formato dd/MM/yyyy/HH/mm "
    )
    @PatchMapping(value="editarSolicitud")
    public boolean editarSolicitud(@RequestBody EditSolicitudRequest request){
        return solicitudService.updateUserSolicitud(request);
    }


    /////////////////////////////////////////////VOLUNTARIO///////////////////////
    @Operation(
        summary = "Obtener las Solicitudes que escogio un usuario Voluntario Responsable ",
        description = "Importante: En la salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor.  "
    )
    @PostMapping(value="getAllSolicitudesResponsable")
    public List<SolicitudResponse> getAllSolicitudesResponsable(@RequestBody UserRequest User) throws ParseException {
        return solicitudService.getSolicitudResponsable(User);
    }


    @Operation(
        summary = "Obtener las Solicitudes que escogio un usuario Volutnario Colaborador ",
        description = "Importante: En la salida: el atributo 'estado' puede tener los siguientes valores: SinResponsable, Pendiente, Realizado. Los atributos nombreU, apellidoU, telefonoU son datos el usuario Receptor."
    )
    @PostMapping(value="getAllSolicitudesColaborador")
    public List<SolicitudResponse> getAllSolicitudesColaborador(@RequestBody UserRequest User) throws ParseException {
        return solicitudService.getSolicitudesColaborador(User);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////// ALIMENTOS Y PRODUCTOS
    @Operation(
        summary = "Para terminar una solicitud y realizar un registro de los alimentos",
        description = ""
    )
    @PostMapping(value="terminarSolicitudAlimentos")
    public boolean terminarSolicitudAlimentos(@RequestBody List<AlimentoSolFinishResponse> listaAli) throws ParseException {
        return solicitudService.terminarSolicitudAlimentos(listaAli);
    }


    @Operation(
        summary = "Para terminar una solicitud y realizar un registro de los Productos",
        description = ""
    )
    @PostMapping(value="terminarSolicitudProdcuctos")
    public boolean terminarSolicitudProdcuctos(@RequestBody List<ProductoSolFinishResponse> listaProd) throws ParseException {
        return solicitudService.terminarSolicitudProductos(listaProd);
    }




}
