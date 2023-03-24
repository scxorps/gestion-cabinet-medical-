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

        String query = "SELECT * FROM patient";
        // Open a connection
        try {
            int i = 0;
            Statement stmtCount = conn.createStatement();
            ResultSet rsCount = stmtCount.executeQuery("select count(*) from patient");

            rsCount.next();

            Object patient[][] = new Object[rsCount.getInt(1)][8];

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

            Object patient[][] = new Object[rsCount.getInt(1)][8];

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

            Object patient[][] = new Object[rsCount.getInt(1)][8];

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                patient[i][0] = rs.getString("place");
                patient[i][1] = rs.getString("nom_p");
                patient[i][2] = rs.getString("prenom_p");
                patient[i][3] = rs.getString("age");
                patient[i][4] = rs.getString("sexe");
                patient[i][5] = rs.getString("n_tel");
                patient[i][6] = rs.getString("adresse");
                patient[i][7] = rs.getString("n_patient");

                i++;
            }
            return patient;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean InsertPatient(String name, String surname, String age, String sexe, String telephone, String adresse,
            Date d) {
        String query = "INSERT INTO patient (nom_p, prenom_p, age, sexe, n_tel, adresse, date_r) VALUES ('" + name
                + "', '"
                + surname + "', '" + age + "', '" + sexe + "', '" + telephone + "', '" + adresse + "','" + d + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean InsertWaitingRoom(String id) {
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
            String query = "INSERT INTO waiting_room (n_patient, place) VALUES('" + id + "', " + place + ")";
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
            String adresse, Date d) {
        String query = "UPDATE patient SET nom_p = '" + nom + "', prenom_p = '" + prenom + "', age = '" + age
                + "', sexe = '" + sexe + "', n_tel = " + n_tel + ", adresse ='" + adresse + "', date_r='" + d + "' WHERE n_patient = " + id;
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
}
