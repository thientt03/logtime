package k62.vhc.demo.login;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class LoginController {
    @Autowired
    public LoginService loginService;
    @ResponseBody
    @RequestMapping(path = "/login")
    public String get_login(@RequestParam("username") String username, @RequestParam("password") String password){
        Gson gson = new Gson();
        return gson.toJson(loginService.get_login(username, password));
    }
}
