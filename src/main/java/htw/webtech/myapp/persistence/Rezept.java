package htw.webtech.myapp.persistence;

import jakarta.persistence.*;

@Entity
public class Rezept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameRezept;
    @Column(nullable = false)
    private String anleitungRezept;
    @Column(columnDefinition = "TEXT") // Postgres große Texte erlaubt
    private String bild;
    private String kategorie;
    private String dauer;

    public Rezept() {}

    public Rezept(String nameRezept, String anleitungRezept, String bild, String kategorie, String dauer) {
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
        this.bild = bild;
        this.kategorie = kategorie;
        this.dauer = dauer;
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
    public String getKategorie() { return kategorie; }
    public void setKategorie(String kategorie) { this.kategorie = kategorie; }
    public String getDauer() { return dauer; }
    public void setDauer(String dauer) { this.dauer = dauer; }

}