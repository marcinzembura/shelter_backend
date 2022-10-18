package pl.shelter.shelter.owner;

import lombok.Getter;
import lombok.Setter;
import pl.shelter.shelter.animal.Animal;

import javax.persistence.*;

@Entity
@Table(name="owner")
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String pesel;
    private String address;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private Animal animal;


    public Owner(String name, String surname, String phoneNumber, String email, String pesel, String address, Animal animal) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
        this.address = address;
        this.animal=animal;
    }

    public Owner() {
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", pesel=" + pesel +
                ", address='" + address + '\'' +
                '}';
    }
}
