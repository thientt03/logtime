package k62.vhc.apiboot.checkin;

import k62.vhc.apiboot.ultis.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;

@Repository
public class CheckInRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Transactional
    public Message checkin_overtime(String username, Date start_time, Date end_time, String address, String status, String note) {
        Query query = this.entityManager.createNativeQuery("insert into time_work(username,start_time,end_time,address,status) values (?, ?, ?, ?,?,?)");
        query.setParameter(1, username);
        query.setParameter(2, start_time);
        query.setParameter(3, end_time);
        query.setParameter(4, address);
        query.setParameter(5, status);
        query.setParameter(6, note);
        try {
            query.executeUpdate();
            return new Message("true", "");
        }
        catch (Exception e) {
            return new Message("false", e.getMessage());
        }
    }

}
