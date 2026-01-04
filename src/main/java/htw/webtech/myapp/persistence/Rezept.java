package htw.webtech.myapp.persistence;

import jakarta.persistence.*;

@Entity
public class Rezept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRezept;
    private String anleitungRezept;
    @Column(columnDefinition = "TEXT") // Postgres große Texte erlaubt
    private String bild;

    public Rezept() {}

    public Rezept(String nameRezept, String anleitungRezept, String bild) {
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
        this.bild = bild;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNameRezept() { return nameRezept; }
    public void setNameRezept(String nameRezept) { this.nameRezept = nameRezept; }
    public String getAnleitungRezept() { return anleitungRezept; }
    public void setAnleitungRezept(String anleitungRezept) { this.anleitungRezept = anleitungRezept; }
    public String getBild() { return bild; }
    public void setBild(String bild) { this.bild = bild; }

}