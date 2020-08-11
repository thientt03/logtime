package k62.vhc.demo.work_time;

import k62.vhc.demo.ultis.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinService {
    @Autowired
    private CheckinRepository checkinRepository;

    public Message check_in(String username, String start_time,String end_time,String status, String address, String note){
        return checkinRepository.checkin_time(username, start_time,end_time, status, address, note);
    }

    public Message update_time_work(String username,String end_time , String start_time) {
        return checkinRepository.update_time_work(username, end_time, start_time);
    }

    public Message checkin_timework_n(Timework[] time_works) {
        return checkinRepository.checkin_timework_n(time_works);
    }

    public List<Timework> work_time(String username, String start_time){
        return checkinRepository.get_work_time(username, start_time);
    }

//    public Timework work_time_n(String username, String start_time, String end_time){
//        return checkinRepository.get_work_time_n(username, start_time, end_time);
//    }
}
