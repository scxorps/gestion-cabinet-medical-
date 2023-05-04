package BDD;

import java.sql.*;

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

        String query = "SELECT * FROM patient P, doctor D where P.doctor_id = D.doctor_id";
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
                patient[i][8] = rs.getString("nom");

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
        String query = "INSERT INTO patient (nom_p, prenom_p, age, sexe, n_tel, adresse, doctor_id, date_r) VALUES ('" + name
                + "', '"
                + surname + "', '" + age + "', '" + sexe + "', '" + telephone + "', '" + adresse + "',"+ dc + ",'" + d + "')";
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

    String query = "SELECT * FROM doctor";
    // Open a connection
    try {
        int i = 0;
        Statement stmtCount = conn.createStatement();
        ResultSet rsCount = stmtCount.executeQuery("select count(*) from doctor");

        rsCount.next();

        Object doctor[][] = new Object[rsCount.getInt(1)][8];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            doctor[i][0] = rs.getInt("doctor_id");
            doctor[i][1] = rs.getString("nom");
            doctor[i][2] = rs.getString("prenom");
            
            i++;
        }
        return doctor;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public String[] getSingleDoctor(String name) {

    String query = "SELECT * FROM doctor where nom='" + name +"'";
    // Open a connection
    try {

        String[] doctor = new String[3];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            doctor[0] = rs.getString("doctor_id");
            doctor[1] = rs.getString("nom");
            doctor[2] = rs.getString("prenom");
          
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

        Object patient[][] = new Object[rsCount.getInt(1)][8];

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            patient[i][0] = rs.getString("id");
            patient[i][1] = rs.getString("name");
            patient[i][2] = rs.getString("surname");
            patient[i][3] = rs.getString("username");
            patient[i][4] = rs.getString("password");
            patient[i][5] = rs.getString("age");
            patient[i][6] = rs.getString("Role");
            patient[i][7] = rs.getString("date_recrut");

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
}
