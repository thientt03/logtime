package k62.vhc.demo.work_time;

import k62.vhc.demo.ultis.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CheckinRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager manager;
    public String string(Object o) {
        return o == null ? null : o.toString();
    }

    @Transactional
    public Message checkin_time(String username, String start_time,String end_time, String status, String address, String note){
        Query query = this.manager.createNativeQuery("insert into work_time(username,start_time, end_time, status, address, note) values (?, to_date(?, 'DD-MM-YYYY hh24:mi:ss'), to_date(?, 'DD-MM-YYYY hh24:mi:ss'),?,?,?)");
        query.setParameter(1,username);
        query.setParameter(2,start_time);
        query.setParameter(3,end_time);
        query.setParameter(4,status);
        query.setParameter(5,address);
        query.setParameter(6,note);

        try{
            query.executeUpdate();
            return new Message("Y","done");
        }
        catch (Exception e){
            return new Message("N",e.getMessage());
        }
    }

    @Transactional
    public Message update_time_work(String username, String end_time, String start_time ) {
        Query query = this.manager.createNativeQuery("update work_time set end_time = to_date(?,'DD-MM-YYYY') where username =? and trunc(start_time) = to_date(?,'DD-MM-YYYY')");
        query.setParameter(1, end_time);
        query.setParameter(2, username);
        query.setParameter(3, start_time);

        try {
            query.executeUpdate();
            return new Message("true", "");
        }
        catch (Exception e) {
            return new Message("false", e.getMessage());
        }
    }



    @Transactional
    public Message checkin_timework_n(Timework[] time_works) {
        String sql_string = "insert all\n";
        for (int i = 0; i < time_works.length; i++) {
            sql_string += "into work_time (username, start_time, end_time, status, address, note) values ("
                    + "'" + time_works[i].getUsername() + "', "
                    + "to_date('" + time_works[i].getStart_time() + "', 'DD-MM-YYYY hh24:mi:ss'), "
                    + "to_date('" + time_works[i].getEnd_time() + "', 'DD-MM-YYYY hh24:mi:ss'), "
                    + "'" + time_works[i].getStatus() + "', "
                    + "'" + time_works[i].getAddress() + "', "
                    + "'" + time_works[i].getNote() + "')\n";

        }
        sql_string += "select * from dual";
        System.out.println(sql_string);
        Query query = manager.createNativeQuery(sql_string);
        try {
            query.executeUpdate();
            return new Message("Y", "");
        }
        catch (Exception e) {
            return new Message("N", e.getMessage());
        }
    }

    @Transactional
    public List<Timework> get_work_time(String username, String start_time){
        StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("PK_WORKING_TIME.GET_WORK_LIST");
        storedProcedureQuery.registerStoredProcedureParameter("P_USERNAME", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_START_TIME", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_DATA", void.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("P_USERNAME", username);
        storedProcedureQuery.setParameter("P_START_TIME", start_time);

        List<Timework> result = new ArrayList<>();
        List<Object[]> result1 = storedProcedureQuery.getResultList();
        result1.stream().forEach(record -> {
            Timework timework = new Timework();
            timework.setId(Integer.valueOf(string(record[0])));
            timework.setUsername(string(record[1]));
            timework.setStart_time(string(record[2]));
            timework.setEnd_time(string(record[3]));
            timework.setStatus(string(record[4]));
            timework.setAddress(string(record[5]));
            timework.setNote(string(record[6]));

            result.add(timework);
        });
        return result;
    }
//
//    @Transactional
//    public Timework get_work_time_n(String username, String start_time, String end_time){
//        Query query = manager.createQuery("select wt.START_TIME, wt.STATUS, wt.from WORK_TIME wt where ");
//    }
}
