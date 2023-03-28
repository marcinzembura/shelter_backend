package pl.shelter.shelter.accountandrole.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.shelter.shelter.accountandrole.role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Long phone_number;
    private String email;
//    @Enumerated(EnumType.STRING)
//    private Role role;
    //TODO!
    //IDK CZY TO JEST GIT?!?!
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_role",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public Account(String login, String password, String name, String surname, Long phone_number, String email) {
        this.username = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Account() {
    }

}

