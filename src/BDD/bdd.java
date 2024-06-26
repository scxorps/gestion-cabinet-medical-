package BDD;

import java.sql.*;

import javax.swing.event.InternalFrameAdapter;

import com.mysql.jdbc.util.ResultSetUtil;

public class bdd {

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public bdd() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/med+?", "root", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet authentification(String username, String password) {
        boolean userFound = false;
        String sql = "SELECT * " +
                "FROM user WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                return rs;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public Object[][] getPatients() {

        String query = "SELECT * FROM patient P, user U where P.doctor_id = U.id";
        // Open a connection
        try {
            int i = 0;
            Statement stmtCount = conn.createStatement();
            ResultSet rsCount = stmtCount.executeQuery("select count(*) from patient");

            rsCount.next();

            Object patient[][] = new Object[rsCount.getInt(1)][9];

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                patient[i][0] = rs.getInt("n_patient");
                patient[i][1] = rs.getString("nom_p");
                patient[i][2] = rs.getString("prenom_p");
                patient[i][3] = rs.getString("age");
                patient[i][4] = rs.getString("sexe");
                patient[i][5] = rs.getString("n_tel");
                patient[i][6] = rs.getString("adresse");
                patient[i][7] = rs.getDate("date_r");
                patient[i][8] = rs.getString("name");

                i++;
            }
            return patient;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] getPatientsOnCurrentDay() {

        String query = "SELECT * FROM patient WHERE date_r = CURDATE() AND n_patient NOT IN (SELECT n_patient FROM waiting_room)";
        // Open a connection
        try {
            int i = 0;
            Statement stmtCount = conn.createStatement();
            ResultSet rsCount = stmtCount.executeQuery(
                    "SELECT count(*) FROM patient WHERE date_r = CURDATE() AND n_patient NOT IN (SELECT n_patient FROM waiting_room)");

            rsCount.next();

            Object patient[][] = new Object[rsCount.getInt(1)][9];

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                patient[i][0] = rs.getInt("n_patient");
                patient[i][1] = rs.getString("nom_p");
                patient[i][2] = rs.getString("prenom_p");
                patient[i][3] = rs.getString("age");
                patient[i][4] = rs.getString("sexe");
                patient[i][5] = rs.getString("n_tel");
                patient[i][6] = rs.getString("adresse");
                patient[i][7] = rs.getDate("date_r");
                patient[i][8] = rs.getString("doctor_id");

                i++;
            }
            return patient;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] getWaitingRoom() {

        String query = "SELECT * FROM waiting_room W, patient P where W.n_patient = P.n_patient ";
        // Open a connection
        try {
            int i = 0;
            Statement stmtCount = conn.createStatement();
            ResultSet rsCount = stmtCount.executeQuery("select count(*) from waiting_room");

            rsCount.next();

            Object patient[][] = new Object[rsCount.getInt(1)][7];

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
               
                patient[i][0] = rs.getString("nom_p");
                patient[i][1] = rs.getString("prenom_p");
                patient[i][2] = rs.getString("age");
                patient[i][3] = rs.getString("sexe");
                patient[i][4] = rs.getString("n_tel");
                patient[i][5] = rs.getString("adresse");
                patient[i][6] = rs.getString("n_patient");

                i++;
            }
            return patient;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean InsertPatient(String name, String surname, String age, String sexe, String telephone, String adresse,
            Date d, String dc) {
        String query = "INSERT INTO patient (nom_p, prenom_p, age, sexe, n_tel, adresse, doctor_id, date_r, first_day) VALUES ('" + name
                + "', '"
                + surname + "', '" + age + "', '" + sexe + "', '" + telephone + "', '" + adresse + "',"+ dc + ",'" + d + "', SYSDATE())";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean InsertWaitingRoom(String id, String id_doc) {
        try {
            Statement stmtCount = conn.createStatement();
            ResultSet rsCount = stmtCount.executeQuery("select count(*) as count from waiting_room");

            rsCount.next();

            Statement stmtlast = conn.createStatement();
            ResultSet rslast = stmtlast.executeQuery("select * from waiting_room ORDER BY place DESC LIMIT 1");

            rslast.next();

            int place = Integer.parseInt(rsCount.getString("count")) == 0
                    ? Integer.parseInt(rsCount.getString("count")) + 1
                    : Integer.parseInt(rslast.getString("place")) + 1;
            String query = "INSERT INTO waiting_room (n_patient, place, doctor_id, price) VALUES('" + id + "', " + place + ", " + id_doc +", " + 0 + ")";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deletePatient(String id) {
        String query = "DELETE FROM patient WHERE n_patient = " + id;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteWaitingRoom(String id) {
        String query = "DELETE FROM waiting_room WHERE n_patient = " + id;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePatient(String id, String nom, String prenom, String age, String sexe, String n_tel,
            String adresse, Date d, String doctor) {
        String query = "UPDATE patient SET nom_p = '" + nom + "', prenom_p = '" + prenom + "', age = '" + age
                + "', sexe = '" + sexe + "', n_tel = " + n_tel + ", adresse ='" + adresse + "', date_r='" + d + "', doctor_id = " + doctor + " WHERE n_patient = " + id;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWaitingRoomFull(Date d){

        String query = "SELECT COUNT(*) FROM patient WHERE date_r='" + d + "'";
        try {
            Statement stmtCount = conn.createStatement();
            ResultSet rsCount = stmtCount.executeQuery(query);

            rsCount.next();


            int count = rsCount.getInt(1);

            if(count < 5)
                return false;
            else 
                return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

}
public Object[][] getDoctor() {

    String query = "SELECT * FROM user WHERE Role=\"Doctor\"";
    // Open a connection
    try {
        int i = 0;
        Statement stmtCount = conn.createStatement();
        ResultSet rsCount = stmtCount.executeQuery("select count(*) from user WHERE Role=\"Doctor\"");

        rsCount.next();

        Object doctor[][] = new Object[rsCount.getInt(1)][8];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            doctor[i][0] = rs.getInt("id");
            doctor[i][1] = rs.getString("name");
            doctor[i][2] = rs.getString("surname");
            
            i++;
        }
        return doctor;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public String[] getSingleDoctor(String name) {

    String query = "SELECT * FROM user where name='" + name +"'";
    // Open a connection
    try {

        String[] doctor = new String[3];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            doctor[0] = rs.getString("id");
            doctor[1] = rs.getString("name");
            doctor[2] = rs.getString("surname");
          
        }
        return doctor;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public String[] getDoctorName() {

    String query = "SELECT * FROM user where Role='Doctor'";
    // Open a connection
    try {
        int i = 0;
        Statement stmtCount = conn.createStatement();
        ResultSet rsCount = stmtCount.executeQuery("select count(*) from user where Role='Doctor'");

        rsCount.next();

        String doctor[] = new String[rsCount.getInt(1)];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            doctor[i] = rs.getString("name");
          
            
            i++;
        }
        return doctor;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public String getDoctorId(String name){
    String query = "SELECT * FROM user WHERE name='" + name + "'";
    // Open a connection
    try {
 
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();

        return rs.getString("id");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public Object[][] getWaitingRoomById(String id) {

    String query = "SELECT * FROM waiting_room W, patient P where W.n_patient = P.n_patient AND w.doctor_id = '" + id + "'";
    // Open a connection
    try {
        int i = 0;
        Statement stmtCount = conn.createStatement();
        ResultSet rsCount = stmtCount.executeQuery("select count(*) from waiting_room where doctor_id = '" + id + "'");

        rsCount.next();

        Object patient[][] = new Object[rsCount.getInt(1)][8];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            patient[i][0] = rs.getString("nom_p");
            patient[i][1] = rs.getString("prenom_p");
            patient[i][2] = rs.getString("age");
            patient[i][3] = rs.getString("sexe");
            patient[i][4] = rs.getString("n_tel");
            patient[i][5] = rs.getString("adresse");
            patient[i][6] = rs.getString("price");
            patient[i][7] = rs.getString("n_patient");

            i++;
        }
        return patient;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public Object[][] getUsers() {

    String query = "SELECT * FROM user";
    // Open a connection
    try {
        int i = 0;
        Statement stmtCount = conn.createStatement();
        ResultSet rsCount = stmtCount.executeQuery("select count(*) from user");

        rsCount.next();

        Object patient[][] = new Object[rsCount.getInt(1)][7];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            patient[i][0] = rs.getString("id");
            patient[i][1] = rs.getString("name");
            patient[i][2] = rs.getString("surname");
            patient[i][3] = rs.getString("username");
            patient[i][4] = rs.getString("age");
            patient[i][5] = rs.getString("Role");
            patient[i][6] = rs.getString("date_recrut");

            i++;
        }
        return patient;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }
    public boolean InsertUsers(String name, String surname, String age, String role, String username, String password,
            Date d) {
        String query = "INSERT INTO user (name, surname, age, Role, username, password, date_recrut) VALUES ('" + name
                + "', '"
                + surname + "', '" + age + "', '" + role + "', '" + username + "', '" + password + "','" + d + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteUsers(String id) {
        String query = "DELETE FROM user WHERE id = " + id;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUserInformtion(String id, String nom, String prenom, String username, String password, String age) {
        String query = "UPDATE user SET name = '" + nom + "', surname = '" + prenom + "', age = " + age
                + ", username = '" + username + "', password = '" + password + "' WHERE id = " + id;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSecurityQuestionSet(String id){
        String query = "SELECT count(*) FROM 2fa WHERE id = " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            if(rs.getInt(1) == 0){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean InsertQA(String id, String question, String answer){
        String query = "INSERT INTO 2fa (id, question, answer) VALUES (" + id + ", '" + question + "', '" + answer + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object[] getInformationByUsername(String username){
        String query = "SELECT * FROM user U, 2fa F WHERE U.id = F.id AND U.username = '" + username + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            Object[] information;

            String queryCount = "SELECT COUNT(*) FROM user U, 2fa F WHERE U.id = F.id AND U.username = '" + username + "'";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(queryCount);

            rs2.next();
            if(Integer.parseInt(rs2.getString("COUNT(*)")) == 0){
                information = new Object[1];
                information[0] = "none";

                return information;
            }

            else{
                information = new Object[6];
                rs.next();
                information[0] = rs.getString("name");
                information[1] = rs.getString("surname");
                information[2] = rs.getString("username");
                information[3] = rs.getString("password");
    
                information[4] = rs.getString("question");
                information[5] = rs.getString("answer");
                return information;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }


 public Object[][] getMédicament(){
    String query = "SELECT * FROM medicament";
    String queryn = "SELECT COUNT(*) FROM medicament"; 
    try {
        int i = 0;
        Statement stmtCount = conn.createStatement();
        ResultSet rsCount = stmtCount.executeQuery(queryn);

        rsCount.next();
        //System.out.println("données sql: \t" + rsCount.getInt(1));
        Object trait[][] = new Object[rsCount.getInt(1)][2];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        //System.out.println("données sql: \t" + rs.getInt("N°"));
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
           
            trait[i][0] = rs.getString("N");
            trait[i][1] = rs.getString("Nom");

            i++;
        }
        return trait;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public int getPatientId(String name){
    String query = "SELECT * FROM patient WHERE nom_p='" + name + "'";
    // Open a connection
    try {
 
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();

        return rs.getInt("n_patient");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}


public boolean InsertCons(int num, String anc, String cons, String ord, String prix) {
        String query = "INSERT INTO consultation (n_patient, anc_maladie, notes_cons, ordonnance, prix) VALUES ('" + num
                + "', '" + anc + "', '" + cons + "', '" + ord + "', '" + prix + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean UpdateCons(int num, String anc, String cons, String ord, String prix) {
        String query = "UPDATE consultation SET anc_maladie = '"+ anc + "', notes_cons = '"+ cons +"', ordonnance = '"+ ord + "', prix = '" + prix + "'WHERE n_patient = "+ num;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

public String getAnc(int num){
    String query = "SELECT anc_maladie FROM consultation WHERE n_patient = '"+ num +"'";
    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();
        String anc = rs.getString("anc_maladie");

        return anc;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public String getntCons(int num){
    String query = "SELECT notes_cons FROM consultation WHERE n_patient = '"+ num +"'";
    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();
        String nt = rs.getString("notes_cons");

        return nt;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public String getOrd(int num){
    String query = "SELECT ordonnance FROM consultation WHERE n_patient = '"+ num +"'";
    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();
        String ord = rs.getString("ordonnance");

        return ord;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public int getPrix(int num){
    String query = "SELECT prix FROM consultation WHERE n_patient = '"+ num +"'";
    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();
        int pr = rs.getInt("prix");

        return pr;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public int getTotPrix(){
    String query = "SELECT prix FROM consultation";
    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        int p = 0;
        while (rs.next()) {
            p += rs.getInt("prix");
        }
        return p;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;

}

public boolean isPatientSubsMoreThanYear(String id){
   String sql = "SELECT count(*) FROM patient WHERE DATEDIFF(CURDATE(), first_day) > 365 AND n_patient ='" + id + "'";
    try {
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         rs.next();
         if(Integer.parseInt(rs.getString(1)) > 0){
              return true;
         }
         else{
              return false;
         }
    } catch (SQLException e) {
         e.printStackTrace();
         return false;
    }
}

public boolean isPatientPassedBefore(String id){
    String sql = "SELECT count(*) FROM consultation WHERE n_patient ='" + id + "' ";
    try {
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         rs.next();
         if(Integer.parseInt(rs.getString(1)) > 0){
              return true;
         }
         else{
              return false;
         }
    } catch (SQLException e) {
         e.printStackTrace();
         return false;
}
}
}