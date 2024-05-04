package spring.learn.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parents")
public class Parents extends User {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long id;
    private String profession;



    public Parents(String profession) {
        this.profession = profession;
    }

    public Parents(Integer userId, String profession) {
        super(userId);
        this.profession = profession;
    }

    public Parents(String password, String username, String userEmail, Date createdOn, String profession) {
        super(password, username, userEmail, createdOn);
        this.profession = profession;
    }

    public Parents(String password, String username, String userEmail, String firstName, String lastName,
            Date createdOn, String profession) {
        super(password, username, userEmail, firstName, lastName, createdOn);
        this.profession = profession;
    }

    public Parents(String password, String username, String userEmail, String firstName, String lastName, Integer id,
            String emailList, String profession) {
        super(password, username, userEmail, firstName, lastName, id, emailList);
        this.profession = profession;
    }

    public Parents() {
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
