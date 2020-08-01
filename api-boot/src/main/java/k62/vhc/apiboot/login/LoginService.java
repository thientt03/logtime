package k62.vhc.apiboot.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    public Login get_login(String username, String password) {
        return loginRepository.get_login(username, password);
    }

}
