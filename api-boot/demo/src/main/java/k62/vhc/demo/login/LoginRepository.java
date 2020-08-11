package k62.vhc.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static k62.vhc.demo.login.CryptWithMD5.cryptWithMD5;

@Repository
public class LoginRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
    @Autowired
    private static CryptWithMD5 cryptWithMD5;
    public Login get_login(String username, String password){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PK_LOGIN.GET_LIST");
        storedProcedureQuery.registerStoredProcedureParameter("P_USERNAME", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_PASSWORD", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("P_DATA", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("P_USERNAME", username);
        String md5 = cryptWithMD5(password);
        storedProcedureQuery.setParameter("P_PASSWORD", md5);
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
