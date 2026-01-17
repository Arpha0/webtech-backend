package htw.webtech.myapp.rest.model;

public class RezeptDTO {
    private Long id;
    private String nameRezept;
    private String anleitungRezept;
    private String bild;
    private String kategorie;
    private String dauer;
    private Long userId; // NEU

    public RezeptDTO() {}
    public RezeptDTO(Long id, String nameRezept, String anleitungRezept, String bild, String kategorie, String dauer, Long userId) {
        this.id = id;
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
        this.bild = bild;
        this.kategorie = kategorie;
        this.dauer = dauer;
        this.userId = userId;
    }

    // Alle Getter & Setter (inklusive getUserId / setUserId)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public Long getUserId() { return userId; }
    public void setUserId(Long id) { this.userId = id; }
}