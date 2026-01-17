package htw.webtech.myapp.business.service;

import htw.webtech.myapp.persistence.*;
import htw.webtech.myapp.rest.model.RezeptDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezeptService {
    private final RezeptRepository rezeptRepository;
    private final UserRepository userRepository;

    public RezeptService(RezeptRepository rr, UserRepository ur) {
        this.rezeptRepository = rr;
        this.userRepository = ur;
    }

    public List<RezeptDTO> getAllRezept() {
        return rezeptRepository.findAll().stream()
                .map(r -> new RezeptDTO(r.getId(), r.getNameRezept(), r.getAnleitungRezept(), r.getBild(), r.getKategorie(), r.getDauer(),
                        r.getOwner() != null ? r.getOwner().getId() : null))
                .collect(Collectors.toList());
    }

    public RezeptDTO createRezept(RezeptDTO rezeptDTO) {
        // 1. DTO in Entity umwandeln (OHNE ID, die vergibt die Datenbank)
        Rezept rezept = new Rezept(
                rezeptDTO.getNameRezept(),
                rezeptDTO.getAnleitungRezept(),
                rezeptDTO.getBild(),
                rezeptDTO.getKategorie(),
                rezeptDTO.getDauer()
        );

        // 2. Speichern (KORRIGIERT: Muss rezeptRepository sein, nicht userrepository)
        Rezept savedRezept = rezeptRepository.save(rezept);

        // 3. Zurück in DTO wandeln (KORRIGIERT: Parameteranzahl angepasst)
        return new RezeptDTO(
                savedRezept.getId(),
                savedRezept.getNameRezept(),
                savedRezept.getAnleitungRezept(),
                savedRezept.getBild(),
                savedRezept.getKategorie(),
                savedRezept.getDauer(),
                null // Owner ID ist bei neuen Rezepten erst mal null
        );
    }

    public void deleteRezept(Long id) { rezeptRepository.deleteById(id); }

    public RezeptDTO updateRezept(Long id, RezeptDTO dto) {
        Rezept r = rezeptRepository.findById(id).orElseThrow();
        r.setNameRezept(dto.getNameRezept());
        r.setAnleitungRezept(dto.getAnleitungRezept());
        r.setBild(dto.getBild());
        r.setKategorie(dto.getKategorie());
        r.setDauer(dto.getDauer());

        Rezept updated = rezeptRepository.save(r);

        // KORRIGIERT: Null-Check für Owner (wie in getAllRezept), sonst Crash
        return new RezeptDTO(
                updated.getId(),
                updated.getNameRezept(),
                updated.getAnleitungRezept(),
                updated.getBild(),
                updated.getKategorie(),
                updated.getDauer(),
                updated.getOwner() != null ? updated.getOwner().getId() : null
        );
    }
}