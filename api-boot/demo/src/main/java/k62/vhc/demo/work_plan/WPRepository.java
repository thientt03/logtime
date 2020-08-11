package k62.vhc.demo.work_plan;

import k62.vhc.demo.ultis.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WPRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager manager;
    public String string(Object o) {
        return o == null ? null : o.toString();
    }


    @Transactional
    public List<WorkPlan> workplan_list(String nguoi_thuc_thi, String status, String actual_sdate, String actual_edate){
        StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("PK_WORK_PLAN.GET_WORK_PLAN");
        storedProcedureQuery.registerStoredProcedureParameter("P_NGUOI_THUC_THI", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_STATUS", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_ACTUAL_SDATE", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_ACTUAL_EDATE", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_DATA", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("P_NGUOI_THUC_THI", nguoi_thuc_thi);
        storedProcedureQuery.setParameter("P_STATUS",status);
        storedProcedureQuery.setParameter("P_ACTUAL_SDATE", actual_sdate);
        storedProcedureQuery.setParameter("P_ACTUAL_EDATE", actual_edate);

        List<WorkPlan> result = new ArrayList<>();
        List<Object[]> result1 = storedProcedureQuery.getResultList();
        result1.stream().forEach(record -> {
            WorkPlan workPlan = new WorkPlan();
            workPlan.setId(string(record[0]));
            workPlan.setWork_code(string(record[1]));
            workPlan.setWork_name(string(record[2]));
            workPlan.setModule_id(Integer.valueOf(string(record[3])));
            workPlan.setWork_type(string(record[4]));
            workPlan.setWork_level(string(record[5]));
            workPlan.setWork_group(string(record[6]));
            workPlan.setNguoi_thuc_thi(string(record[7]));
            workPlan.setNguoi_theo_doi(string(record[8]));
            workPlan.setNguoi_giao(string(record[9]));
            workPlan.setTester(string(record[10]));
            workPlan.setCustomer(string(record[11]));
            workPlan.setAssign_sdate(string(record[12]));
            workPlan.setAssign_edate(string(record[13]));
            workPlan.setActual_sdate(string(record[14]));
            workPlan.setActual_edate(string(record[15]));
            workPlan.setContent_plan(string(record[16]));
            workPlan.setFile_id(string(record[17]));
            workPlan.setStatus(string(record[18]));
            workPlan.setProgress(Integer.valueOf(string(record[19])));
            workPlan.setNote(string(record[20]));
            workPlan.setCreate_by(string(record[21]));
            workPlan.setCreate_date(string(record[22]));
            workPlan.setModify_by(string(record[23]));
            workPlan.setModify_date(string(record[24]));
            workPlan.setProject_id(Integer.valueOf(string(record[25])));

//          workPlan.setWork_parent_id(Integer.valueOf(string(record[26])));

            workPlan.setRate(Integer.valueOf(string(record[27])));
            workPlan.setFile_id_finish(string(record[28]));

            result.add(workPlan);
        });
        return result;
    }
    @Transactional
    public Message workplan_update(String nguoi_thuc_thi, String progress, String status){
        Query query = this.manager.createNativeQuery("update work_plan set progress = ?, status = ? where nguoi_thuc_thi = ?");
        query.setParameter(1,progress);
        query.setParameter(2,status);
        query.setParameter(3,nguoi_thuc_thi);
        try{
            query.executeUpdate();
            return new Message("Y","done");
        }catch (Exception e){
            return new Message("N",e.getMessage());
        }
    }

}
