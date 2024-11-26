package tacos1.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos1.entity.User;
//Класс нужен что бы обрабатывать ХТМЛ форму и переводить ее в сущность, что бы потом в контроллере сохранить ее в БД
@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    //этот метод нужен для шифрования пароля перед передачей ешл в БД
    public User toUser(PasswordEncoder passEncod){
        return new User(username, passEncod.encode(password),
                fullname, street, city, state, zip, phone);
    }
}
