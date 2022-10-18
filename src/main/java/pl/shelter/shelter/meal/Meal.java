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
    private Integer weight;
    private String date;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private Animal animal;

    public Meal(String name, Integer weight, String date, Animal animal) {
        this.name = name;
        this.weight = weight;
        this.date = date;
        this.animal=animal;
    }

    public Meal() {
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", date='" + date + '\'' +
                ", animal_id=" + animal.getId() +
                '}';
    }
}
