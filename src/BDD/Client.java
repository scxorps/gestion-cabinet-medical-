package BDD;


import java.sql.Date;

public class Client extends Personne {
    private int id;
  


    public Client(int id, Date recrutDate, String name, String surname, int age, String username, String password){
        super(name, surname, age);

        this.id = id;
        

    }
    public int getId(){
        return id;
    }
    
}
