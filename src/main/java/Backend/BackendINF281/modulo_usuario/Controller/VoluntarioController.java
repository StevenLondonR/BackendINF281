package Backend.BackendINF281.modulo_usuario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.modulo_usuario.Service.UserService;
import Backend.BackendINF281.modulo_usuario.Service.VoluntarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name="Voluntario_Controller(/voluntarioUser)", description = "Operaciones como: obtener voluntarios, Voluntario responsable quita y escoge donacion y establece nro de voluntarios colaboradores, Voluntario Colaborador quita y escoge donacion  ")
@RestController
@RequestMapping("/voluntarioUser")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})  
public class VoluntarioController {

    private final UserService userService;
    private final VoluntarioService voluntarioService;

    @Operation(
        summary = "Obtener todos los voluntario de la entidad voluntario", 
        description ="En la salida, el estado : \n estadoGeneralUser => se refiere al estado del usuario(Habilitado, Inabiltado, Pendiente, Rechazado), \n -El atributo subrol => se refiere la subrol de un usuario Voluntario, 'subrol' puede obtener los siguientes valores: \" \" o Responsable o Colaborador o Responsable,Colaborador "
    )
    @GetMapping(value="getAllVoluntarios")
    public List<VoluntarioAllResponse> getAllVoluntarios() {
        return userService.obtenerAllVoluntarios();
    }

    
    @Operation(
        summary = "Que un usuario voluntario Responsable puede escoger donacion", 
        description ="( Modifica la tabla Donacion )Un usuario que ya es un voluntario  con subrol de Responsable puede elegir una donacion de la cual quiere ser Responsable "
    )
    @PostMapping(value="escogerDonResponsable")
    public boolean escogerDonResponsable(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.escogerDonacionResponsable(request);
    }
    
    @Operation(
        summary = "Que un usuario voluntario Responsable puede quitar donacion escogida", 
        description ="( Modifica la tabla Donacion )Un usuario que ya es un voluntario con subrol de Responsable puede quitar una donacion de la cual ya lo habia elegido antes."
    )
    @PostMapping(value="quitarDonResponsable")
    public boolean quitarDonResponsable(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.quitarDonacionResponsable(request);
    }

    @Operation(
        summary = "El usuario voluntario Responsable puede establecer el numero de voluntarios colaboradores que se necesitan", 
        description ="( Modifica la tabla Donacion )Un usuario que ya es un voluntario con subrol de Responsable modificara el nro de colaboradores"
    )
    @PostMapping(value="establecerNroVolDonC")
    public boolean establecerNroVolDonC(@RequestBody ResponsableNroVolRequest request) {
        return voluntarioService.establecerNroVolC(request);
    }
    ////////////////////////////////////////////////////////////////////////////////

    @Operation(
        summary = "Para que un usuario voluntario colaborador puede escoger donacion ", 
        description ="( Modifica la tabla Donacion )Un usuario que ya es un voluntario con subrol de responsable puede escoger una donacion que ya tenga un Voluntario Responsable y ya este establecido el nro de Colaboradores que se necesitan. "
    )
    @PostMapping(value="escogerDonColaborador")
    public boolean escogerDonColaborador(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.escogerDonacionColaborador(request);
    }

    @Operation(
        summary = "Para que un usuario voluntario Colaborador puede quitar donacion escogida", 
        description ="Un usuario que ya es un voluntario con subrol de Colaborador puede quitar una donacion de la cual ya elegido antes."
    )
    @PostMapping(value="quitarDonColaborador")
    public boolean quitarDonColaborador(@RequestBody VolEscogerDonRequest request) {
        return voluntarioService.quitarDonacionColaborador(request);
    }

/////////////////////////////////////////////////////////// SOLICITUDES ///////////////////////////////////////////////////////////////

    @Operation(
        summary = "Para que un usuario voluntario Responsable puede escoger Solicitud", 
        description ="Un usuario que ya es un voluntario  con subrol de Responsable puede elegir una Solicitud de la cual quiere ser Responsable "
    )
    @PostMapping(value="escogerSolResponsable")
    public boolean escogerSolResponsable(@RequestBody VolEscogerSolRequest request) {
        return voluntarioService.escogerSolicitudResponsable(request);
    }


    @Operation(
        summary = "Para que un usuario voluntario Responsable puede quitar Solicitud escogida", 
        description ="Un usuario que ya es un voluntario con subrol de Responsable puede quitar una Solicitud de la cual ya lo habia elegido antes."
    )
    @PostMapping(value="quitarSolResponsable")
    public boolean quitarSolResponsable(@RequestBody VolEscogerSolRequest request) {
        return voluntarioService.quitarSolicitudResponsable(request);
    }

    @Operation(
        summary = "El usuario voluntario Responsable puede establecer el numero de voluntarios colaboradores que se necesitan", 
        description ="Un usuario que ya es un voluntario con subrol de Responsable modificara el nro de colaboradores"
    )
    @PostMapping(value="establecerNroVolSolC")
    public boolean establecerNroVolSolC(@RequestBody ResponsableNroVolSolRequest request) {
        return voluntarioService.establecerNroVolSolC(request);
    }

    ////////////////////////////////////////////////////////////////////////////////

    @Operation(
        summary = "Que un usuario voluntario colaborador puede escoger Solicitud ", 
        description ="Un usuario que ya es un voluntario con subrol de responsable puede escoger una Solicitud que ya tenga un Voluntario Responsable y ya este establecido el nro de Colaboradores que se necesitan. "
    )
    @PostMapping(value="escogerSolColaborador")
    public boolean escogerSolColaborador(@RequestBody VolEscogerSolRequest request) {
        return voluntarioService.escogerSolicitudColaborador(request);
    }

    @Operation(
        summary = "Que un usuario voluntario Colaborador puede quitar Solicitud escogida", 
        description ="Un usuario que ya es un voluntario con subrol de Colaborador puede quitar una Solicitud de la cual ya elegido antes."
    )
    @PostMapping(value="quitarSolColaborador")
    public boolean quitarSolColaborador(@RequestBody VolEscogerSolRequest request) {
        return voluntarioService.quitarSolicitudColaborador(request);
    }



}
