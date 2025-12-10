package htw.webtech.myapp.persistence;

import jakarta.persistence.*;

@Entity
public class Rezept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRezept;
    private String anleitungRezept;

    public Rezept() {}

    public Rezept(String nameRezept, String anleitungRezept) {
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNameRezept() { return nameRezept; }
    public void setNameRezept(String nameRezept) { this.nameRezept = nameRezept; }
    public String getAnleitungRezept() { return anleitungRezept; }
    public void setAnleitungRezept(String anleitungRezept) { this.anleitungRezept = anleitungRezept; }
}