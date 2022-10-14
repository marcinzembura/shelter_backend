package pl.shelter.shelter.medicalCard;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.shelter.shelter.animal.Animal;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "medical_card")
@Getter
@Setter
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameOfDisease;
    private String date;
    private String description;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private Animal animal;

    public MedicalCard(String nameOfDisease, String date, String description, Animal animal) {
        this.nameOfDisease = nameOfDisease;
        this.date = date;
        this.description = description;
        this.animal = animal;
    }

    public MedicalCard() {
    }

    @Override
    public String toString() {
        return "MedicalCard{" +
                "id=" + id +
                ", nameOfDisease='" + nameOfDisease + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", id_animal=" + animal.getId() +
                '}';
    }
}
