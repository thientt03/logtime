package k62.vhc.demo.work_time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timework {
    private Integer id;
    private String username;
    private String start_time;
    private String end_time;
    private String status;
    private String address;
    private String note;
}
