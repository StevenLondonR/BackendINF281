package Backend.BackendINF281.Mensajes.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.Mensajes.Controller.UsuarioPostResponse;
import Backend.BackendINF281.modulo_usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensajesService {

    private final UsuarioRepository UserRepository;

    public List<UsuarioPostResponse> obtenerUsersPost(){

        return UserRepository.getUsersPost();
        //return null;
    }

    @Transactional
    public String acceptUser(Integer id) {
        UserRepository.updateAcceptUser(id,"Habilitado");
        return "Usuario Aceptado";
    }

    @Transactional
    public String refusedUser(Integer id) {
        UserRepository.updateRefusedUser(id);
        return "Usuario Rechazado";
    }

    @Transactional
    public String inabilitarUser(Integer id) {
        UserRepository.updateInabilitarUser(id);
        return "Usuario Inabilitado";
    }
    @Transactional
    public String deleteUser(Integer id) {
        UserRepository.deleteByidUsuario(id);
        return "Usuario Eliminado";
    }

}
