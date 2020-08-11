package k62.vhc.demo.login;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Login {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String sex;
    private String position;
    private String is_enable;
    private String team;
    private String create_by;
    private Date create_date;
    private String role;
}