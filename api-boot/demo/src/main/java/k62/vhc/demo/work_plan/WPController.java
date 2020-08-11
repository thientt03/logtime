package k62.vhc.demo.work_plan;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class WPController {
    @Autowired
    private WPService wpService;
    //done
    @ResponseBody
    @RequestMapping(path = "/workplanupdate")
    public String workplan_update(
            @RequestParam("nguoi_thuc_thi") String nguoi_thuc_thi,
            @RequestParam("progress") String progress,
            @RequestParam("status") String status
    ){
        Gson gson = new Gson();
        return gson.toJson(wpService.work_plan_update(nguoi_thuc_thi, progress, status));
    }
    //done
    @ResponseBody
    @RequestMapping(path = "/workplanlist")
    public List<WorkPlan> workplan_list(
            @RequestParam("nguoi_thuc_thi") String nguoi_thuc_thi,
            @RequestParam("status") String status,
            @RequestParam("actual_sdate") String actual_sdate,
            @RequestParam("actual_edate") String actual_edate
    ){
//        Gson gson = new Gson();
        return (wpService.work_plan_list(nguoi_thuc_thi, status, actual_sdate, actual_edate));
    }
}
