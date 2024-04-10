package Backend.BackendINF281.DonacionSolicitud.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Backend.BackendINF281.DonacionSolicitud.Controller.DonacionRequest;
import Backend.BackendINF281.DonacionSolicitud.Controller.DonacionResponse;
import Backend.BackendINF281.DonacionSolicitud.Models.Donacion;
import Backend.BackendINF281.DonacionSolicitud.Repository.DonacionRepository;
import Backend.BackendINF281.modulo_usuario.Controller.UserRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonacionService {

    DonacionRepository donacionRepository;

    public List<DonacionResponse> getAll() {
        List<Donacion> listDon=donacionRepository.findAll();
        List<DonacionResponse> listResponse=new ArrayList<>();
        for(int i=0;i< listDon.size();i++){
            DonacionResponse donResp=DonacionResponse.builder()
                            .idDon(listDon.get(i).getIddonacion())
                            .nombreU(listDon.get(i).getUsuario().getNombre())
                            .apellidoU(listDon.get(i).getUsuario().getApellido())
                            .cantidad(listDon.get(i).getCantidad())
                            .tipo_ap(listDon.get(i).getTipo_ap())
                            .fechaHoraProg(listDon.get(i).getFecha_hora_adquisicion().toString()) // TODO veficar la posicion de los datos al convertir a string 
                            .estado(verificarEstadoDonacion(listDon.get(i).getIddonacion()))
                            .build();
                    // TODO falta guardar la donacion a las entidades 
        
        }
        return listResponse;
    }

    private String verificarEstadoDonacion(Integer integer) {
        // TODO Completar metodo para verificar el estaod de una Donacion sinVoluntario, pendiente, Realizado
        throw new UnsupportedOperationException("Unimplemented method 'verificarEstadoDonacion'");
    }

    public List<DonacionResponse> getDonRealizados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRealizados'");
    }

    public List<DonacionResponse> getDonRealizados(Integer idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRealizados'");
    }

    public List<DonacionResponse> getDNoRealizados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDNoRealizados'");
    }

    public List<DonacionResponse> getDNoRealizados(Integer idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDNoRealizados'");
    }
    // TODO Agregar getDonPendiente, getDonSinRepsentante para el voluntario repesentante y colaborador
    


    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean realizarD(DonacionRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarD'");
    }

    public boolean deleteDon(UserRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDon'");
    }

}
