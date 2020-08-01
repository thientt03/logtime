package k62.vhc.apiboot.checkin;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/api")

public class CheckInController {
    @Autowired
    private CheckInService checkInService;
    @RequestMapping(path = "/checkin")
    @ResponseBody
    public String checkin_overtime(
            @RequestParam("username") String username,
            @RequestParam("start_time")Date start_time,
            @RequestParam("end_time")Date end_time,
            @RequestParam("status") String status,
            @RequestParam("address") String address,
            @RequestParam("note") String note) {
        Gson gson = new Gson();
        return gson.toJson(checkInService.checkin_overtime(username, start_time,end_time, status, address, note));
    }

}
