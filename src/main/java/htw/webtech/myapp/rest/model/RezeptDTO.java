package htw.webtech.myapp.rest.model;

public class RezeptDTO {

    private Long id;
    private String nameRezept;
    private String anleitungRezept;

    // Leerer Konstruktor (wichtig für Frameworks)
    public RezeptDTO() {}

    // Konstruktor
    public RezeptDTO(Long id, String nameRezept, String anleitungRezept) {
        this.id = id;
        this.nameRezept = nameRezept;
        this.anleitungRezept = anleitungRezept;
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
}