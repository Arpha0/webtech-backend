package htw.webtech.myapp.business.service;

import htw.webtech.myapp.persistence.Rezept;
import htw.webtech.myapp.persistence.RezeptRepository;
import htw.webtech.myapp.rest.model.RezeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezeptService {

    private final RezeptRepository rezeptRepository;

    @Autowired
    public RezeptService(RezeptRepository rezeptRepository) {
        this.rezeptRepository = rezeptRepository;
    }

    public List<RezeptDTO> getAllRezept() {
        return rezeptRepository.findAll().stream()
                .map(rezept -> new RezeptDTO(rezept.getId(), rezept.getNameRezept(), rezept.getAnleitungRezept(), rezept.getBild(), rezept.getKategorie()))
                .collect(Collectors.toList());
    }

    public RezeptDTO createRezept(RezeptDTO rezeptDTO) {
        Rezept rezept = new Rezept(rezeptDTO.getNameRezept(), rezeptDTO.getAnleitungRezept(), rezeptDTO.getBild(), rezeptDTO.getKategorie());
        Rezept savedRezept = rezeptRepository.save(rezept);
        return new RezeptDTO(savedRezept.getId(), savedRezept.getNameRezept(), savedRezept.getAnleitungRezept(), savedRezept.getBild(), savedRezept.getKategorie());
    }

    public void deleteRezept(Long id) {
        rezeptRepository.deleteById(id);
    }

    public RezeptDTO updateRezept(Long id, RezeptDTO rezeptDTO) {
        // 1. Das alte Rezept aus der DB holen (oder Fehler werfen, wenn nicht da)
        var rezept = rezeptRepository.findById(id).orElseThrow();
        // 2. Die neuen Werte setzen
        rezept.setNameRezept(rezeptDTO.getNameRezept());
        rezept.setAnleitungRezept(rezeptDTO.getAnleitungRezept());
        rezept.setBild(rezeptDTO.getBild());
        rezept.setKategorie(rezeptDTO.getKategorie());
        // 3. Speichern
        var updatedRezept = rezeptRepository.save(rezept);
        // 4. In DTO umwandeln und zurückgeben
        return new RezeptDTO(updatedRezept.getId(), updatedRezept.getNameRezept(), updatedRezept.getAnleitungRezept(), updatedRezept.getBild(), updatedRezept.getKategorie());
    }

}