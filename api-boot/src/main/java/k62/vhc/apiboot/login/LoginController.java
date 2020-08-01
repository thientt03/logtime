package k62.vhc.apiboot.login;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping(path = "/login")
    @ResponseBody
    public String get_login(@RequestParam("username") String username, @RequestParam("password") String password){
        Gson gson = new Gson();
        return gson.toJson(loginService.get_login(username, password));
    }
}
