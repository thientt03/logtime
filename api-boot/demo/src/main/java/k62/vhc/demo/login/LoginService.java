package k62.vhc.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    public LoginRepository loginRepository;

    public Login get_login(String username, String password){
        return loginRepository.get_login(username, password);
    }
}
