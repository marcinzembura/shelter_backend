package pl.shelter.shelter.meal;

import lombok.Getter;
import lombok.Setter;
import pl.shelter.shelter.animal.Animal;

import javax.persistence.*;

@Entity
@Table(name="meal")
@Getter
@Setter
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String date;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private Animal animal;

    public Meal(String name,String description, String date, Animal animal) {
        this.name = name;
        this.description=description;
        this.date = date;
        this.animal=animal;
    }

    public Meal() {
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", animal=" + animal +
                '}';
    }
}
