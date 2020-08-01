package k62.vhc.apiboot.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class LoginRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
    public Login get_login(String username, String password){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PK_USERS.GET_USER_LIST");
        storedProcedureQuery.registerStoredProcedureParameter("P_USERNAME", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_PASSWORD", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_DATA", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("P_USERNAME", username);
        storedProcedureQuery.setParameter("P_PASSWORD", password);
        Object[] result = null;
        try {
            result = (Object[]) storedProcedureQuery.getSingleResult();
        }
        catch( NoResultException e) {
            return null;
        }
        Login login = new Login();
        login.setUsername(result[1].toString());
        login.setFullname(result[3].toString());
        login.setEmail(result[4].toString());
        login.setPhone(result[5].toString());
        login.setSex(result[6].toString());
        login.setPosition(result[7].toString());
        login.setIs_enable(result[8].toString());
        login.setTeam(result[9].toString());
        login.setCreate_by(result[10].toString());
        login.setCreate_date(convertString2Timestamp(result[11].toString()));
        login.setRole(result[12].toString());
        return login;
    }
    public Timestamp convertString2Timestamp(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
            return null;
        }
    }

}
//thien.trankhanh
//e10adc3949ba59abbe56e057f20f883e

