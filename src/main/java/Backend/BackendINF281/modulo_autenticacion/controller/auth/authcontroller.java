package Backend.BackendINF281.modulo_autenticacion.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authcontroller {

    @PostMapping(value = "login")
    public String login(){

        return "login endpoint ";

    }

    @PostMapping(value = "register")
    public String register(){
        return "register endpoint";
    }



}
