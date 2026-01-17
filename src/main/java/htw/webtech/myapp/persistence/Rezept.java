package htw.webtech.myapp.persistence;

import jakarta.persistence.*;

@Entity
public class Rezept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRezept;
    @Column(columnDefinition = "TEXT")
    private String anleitungRezept;
    private String bild;
    private String kategorie;
    private String dauer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner; // Verknüpfung zum User

    public Rezept() {}
    public Rezept(String nameRezept, String anleitungRezept, String bild, String kategorie, String dauer) {
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
        this.bild = bild;
        this.kategorie = kategorie;
        this.dauer = dauer;
    }

    // Getter & Setter inklusive owner
    public Long getId() { return id; }
    public Long setId(Long id) { this.id = id; return id; }
    public String getNameRezept() { return nameRezept; }
    public void setNameRezept(String n) { this.nameRezept = n; }
    public String getAnleitungRezept() { return anleitungRezept; }
    public void setAnleitungRezept(String a) { this.anleitungRezept = a; }
    public String getBild() { return bild; }
    public void setBild(String b) { this.bild = b; }
    public String getKategorie() { return kategorie; }
    public void setKategorie(String k) { this.kategorie = k; }
    public String getDauer() { return dauer; }
    public void setDauer(String d) { this.dauer = d; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}