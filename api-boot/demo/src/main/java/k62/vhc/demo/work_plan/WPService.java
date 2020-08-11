package k62.vhc.demo.work_plan;

import k62.vhc.demo.ultis.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WPService {
    @Autowired
    private WPRepository wpRepository;

    public Message work_plan_update(String nguoi_thuc_thi, String progress, String status){
        return wpRepository.workplan_update(nguoi_thuc_thi, progress, status);
    }

    public List<WorkPlan> work_plan_list(String nguoi_thuc_thi, String status, String actual_sdate, String actual_edate){
        return wpRepository.workplan_list(nguoi_thuc_thi, status, actual_sdate, actual_edate);
    }
}
