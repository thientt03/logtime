package k62.vhc.demo.work_plan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPlan {
    private String id;
    private String work_code;
    private String work_name;
    private Integer module_id;
    private String work_type;
    private String work_level;
    private String work_group;
    private String nguoi_thuc_thi;
    private String nguoi_theo_doi;
    private String nguoi_giao;
    private String tester;
    private String customer;
    private String assign_sdate;
    private String assign_edate;
    private String actual_sdate;
    private String actual_edate;
    private String content_plan;
    private String file_id;
    private String status;
    private Integer progress;
    private String note;
    private String create_by;
    private String create_date;
    private String modify_by;
    private String modify_date;
    private Integer project_id;
    private Integer work_parent_id;
    private Integer rate;
    private String file_id_finish;
}
