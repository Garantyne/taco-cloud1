package tacos1.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos1.Repository.UserRepository;
import tacos1.security.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRep;
    private PasswordEncoder passEncod;

    public RegistrationController(UserRepository userRep, PasswordEncoder passEncod){
        this.userRep = userRep;
        this.passEncod = passEncod;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form){
        userRep.save(form.toUser(passEncod));
        return"redirect:/login";
    }
}
