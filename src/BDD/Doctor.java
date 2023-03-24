package BDD;

import java.sql.Date;

public class Doctor extends Personne {
    private int id;
    public final String role = "Doctor";
    private Date recrutDate;
    private String username;
    private String password;


    public Doctor(int id, Date recrutDate, String name, String surname, int age, String username, String password){
        super(name, surname, age);

        this.id = id;
        this.recrutDate = recrutDate;
        this.username = username;
        this.password = password;

    }
    public int getId(){
        return id;
    }
    public Date getRecrutDate(){
        return recrutDate;
    }
    public String getUserame(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
