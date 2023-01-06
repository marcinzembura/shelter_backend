package pl.shelter.shelter.animal;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "animal")
@Getter @Setter @ToString
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String gender;
    private String typeOfAnimal;
    private Integer age;
    private Integer weight;
    private boolean status;
    private String picture;
    private String sector;
    private String date;

    public Animal(String name, String gender,String typeOfAnimal, Integer age, Integer weight, boolean status, String picture, String sector, String date) {
        this.name = name;
        this.gender=gender;
        this.typeOfAnimal = typeOfAnimal;
        this.age = age;
        this.weight = weight;
        this.status = status;
        this.picture = picture;
        this.sector = sector;
        this.date=date;

    }

    public Animal() {
    }
}
