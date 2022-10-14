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
    private String typeOfAnimal;
    private Integer age;
    private Integer weight;
    private boolean status;
    private String picture;
    private String sector;

    public Animal(String name, String typeOfAnimal, Integer age, Integer weight, boolean status, String picture, String sector) {
        this.name = name;
        this.typeOfAnimal = typeOfAnimal;
        this.age = age;
        this.weight = weight;
        this.status = status;
        this.picture = picture;
        this.sector = sector;
    }

    public Animal() {
    }
}
