package k62.vhc.demo.work_time;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CheckinController {
    @Autowired
    private CheckinService checkinService;

    @ResponseBody
    @RequestMapping(path = "/checkin")
    public String checkin_time(
            @RequestParam("username") String username,
            @RequestParam("start_time") String start_time,
            @RequestParam("end_time") String end_time,
            @RequestParam("status") String status,
            @RequestParam("address") String address,
            @RequestParam("note") String note
    ){
        Gson gson = new Gson();
        return gson.toJson(checkinService.check_in(username, start_time, end_time, status, address, note));
    }

    @ResponseBody
    @RequestMapping(path = "/updatetimework")
    public String update_time_work(
            @RequestParam("username") String username,
            @RequestParam("end_time") String end_time,
            @RequestParam("start_time") String start_time) {
        Gson gson = new Gson();
        return gson.toJson(checkinService.update_time_work(username,end_time,start_time));
    }

    @RequestMapping(path = "/checkintimework_n", method=RequestMethod.POST)
    @ResponseBody
    public String checkintimework_n(@RequestBody Timework[] time_works) {
        Gson gson = new Gson();
        return gson.toJson(checkinService.checkin_timework_n(time_works));
    }

    @RequestMapping(path = "/work_time", method = RequestMethod.POST)
    @ResponseBody
    public List<Timework> get_work_time(@RequestParam("username") String username, @RequestParam("start_time") String start_time){
        //Gson gson = new Gson();
        return checkinService.work_time(username,start_time);
    }

    @RequestMapping(path = "/hello")
    public String he(){
        return "hello";
    }

}
