package pl.shelter.shelter.medicalCard;

import lombok.Getter;
import lombok.Setter;
import pl.shelter.shelter.animal.Animal;

import javax.persistence.*;

@Entity
@Table(name = "medical_card")
@Getter
@Setter
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String doctor;
    private String nameOfDisease;
    private String date;
    private String description;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private Animal animal;

    public MedicalCard(String doctor,String nameOfDisease, String date, String description, Animal animal) {
        this.doctor=doctor;
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
                ", doctor='" + doctor + '\'' +
                ", nameOfDisease='" + nameOfDisease + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", animal=" + animal +
                '}';
    }
}
