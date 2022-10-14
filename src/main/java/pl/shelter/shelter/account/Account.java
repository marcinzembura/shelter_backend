package pl.shelter.shelter.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "account")
@Getter @Setter @ToString
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Column(login = "login")
    private String login;

    @JsonIgnore
    //@Column(password = "password")
    private String password;

   // @Column(name = "name")
    private String name;

    //@Column(surname = "surname")
    private String surname;

   // @Column(phone_number = "phone_number")
    private Long phone_number;

   // @Column(email = "email")
    private String email;

  //  @Column(role = "role")
    @Enumerated(EnumType.STRING)
    private Role role;



    public Account(String login, String password, String name, String surname, Long phone_number, String email, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
        this.role = role;
    }

    public Account() {
    }

}

