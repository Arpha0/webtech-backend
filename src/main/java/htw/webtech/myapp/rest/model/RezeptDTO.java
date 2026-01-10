package htw.webtech.myapp.rest.model;

public class RezeptDTO {

    private Long id;
    private String nameRezept;
    private String anleitungRezept;
    private String bild;
    private String kategorie;
    private String dauer;

    // Leerer Konstruktor (wichtig für Frameworks)
    public RezeptDTO() {}

    // Konstruktor
    public RezeptDTO(Long id, String nameRezept, String anleitungRezept, String bild, String kategorie, String dauer) {
        this.id = id;
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
        this.bild = bild;
        this.kategorie = kategorie;
        this.dauer = dauer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRezept() {
        return nameRezept;
    }

    public void setNameRezept(String nameRezept) {
        this.nameRezept = nameRezept;
    }

    public String getAnleitungRezept() {
        return anleitungRezept;
    }

    public void setAnleitungRezept(String anleitungRezept) {
        this.anleitungRezept = anleitungRezept;
    }

    public String getBild() { return bild; }

    public void setBild(String bild) { this.bild = bild; }
    public String getKategorie() { return kategorie; }
    public void setKategorie(String kategorie) { this.kategorie = kategorie; }
    public String getDauer() { return dauer; }
    public void setDauer(String dauer) { this.dauer = dauer; }
}