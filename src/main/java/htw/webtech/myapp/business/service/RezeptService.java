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
                .map(rezept -> new RezeptDTO(Math.toIntExact(rezept.getId()), rezept.getNameRezept(), rezept.getAnleitungRezept()))
                .collect(Collectors.toList());
    }

    public RezeptDTO createRezept(RezeptDTO rezeptDTO) {
        Rezept rezept = new Rezept(rezeptDTO.nameRezept(), rezeptDTO.anleitungRezept());
        Rezept savedRezept = rezeptRepository.save(rezept);
        return new RezeptDTO(Math.toIntExact(savedRezept.getId()), savedRezept.getNameRezept(), savedRezept.getAnleitungRezept());
    }

    public void deleteRezept(Long id) {
        rezeptRepository.deleteById(id);
    }

}