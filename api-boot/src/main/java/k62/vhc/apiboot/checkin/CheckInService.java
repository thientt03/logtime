package k62.vhc.apiboot.checkin;

import k62.vhc.apiboot.ultis.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
@Service
public class CheckInService {
    @Autowired
    private CheckInRepository checkInRepository;
    public Message checkin_overtime(String username, Date start_time, Date end_time, String status, String address, String note ) {
        return checkInRepository.checkin_overtime(username,start_time,end_time ,status,address,note);
    }

}
