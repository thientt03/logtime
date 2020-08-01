package k62.vhc.apiboot.checkin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
public class Timework {
    private int id;
    private String username;
    private Date start_time;
    private Date end_time;
    private String status;
    private String address;
    private String note;


}
