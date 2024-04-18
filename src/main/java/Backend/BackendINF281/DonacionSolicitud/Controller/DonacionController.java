package Backend.BackendINF281.DonacionSolicitud.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.BackendINF281.DonacionSolicitud.Services.DonacionService;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/donaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8090"})
public class DonacionController {

    private final DonacionService donService; 

    @GetMapping(value="getAllDonaciones")
    public List<DonacionResponse> getAllDonaciones() throws ParseException {
        return donService.getAll();
    }
    
    @GetMapping(value="getDonRealizadas")
    public List<DonacionResponse> getDonRealizadas() throws ParseException {
        return donService.getDonRealizados();
    }
    
    @PostMapping(value="getDonRealizadas")
    public List<DonacionResponse> getDonRealizadasU(@RequestBody UserRequest User) throws ParseException {
        return donService.getDonRealizados(User); // TODO cambiar a metodo post con entrada de UserRquest
    }

    @GetMapping(value="getDonNoRealizadas")
    public List<DonacionResponse> getDonNoRealizadasU() throws ParseException {
        return donService.getDNoRealizados();
    }

    @PostMapping(value="getDonNoRealizadas")
    public List<DonacionResponse> getDonNoRealizadasU(@RequestBody UserRequest User) throws ParseException {
        return donService.getDNoRealizados(User);
    }
    // TODO Agregar getDonPendiente, getDonSinRepsentante para el voluntario repesentante y colaborador
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping(value="realizarDonacion")
    public boolean realizarDonacion(@RequestBody DonacionRequest request) {
        return donService.realizarD(request);
    }
/*
    @PostMapping(value="deleteDonacion/{idDonacion}")
    public boolean deleteDonacion(@RequestBody UserRequest request){
        return donService.deleteDon(request);
    }*/
/////////////////////////////////////////////VOLUNTARIO///////////////////////
    @PostMapping(value="getAllDonacionesResponsable")
    public List<DonacionResponse> getAllDonacionesResponsable(@RequestBody UserRequest User) throws ParseException {
        return donService.getDonacionesResponsable(User);
    }

    @PostMapping(value="getAllDonacionesColaborador")
    public List<DonacionResponse> getAllDonacionesColaborador(@RequestBody UserRequest User) throws ParseException {
        return donService.getDonacionesColaborador(User);
    }


}
