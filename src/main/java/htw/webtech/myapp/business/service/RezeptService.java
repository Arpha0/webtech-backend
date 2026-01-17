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

    public RezeptDTO createRezept(RezeptDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Rezept r = new Rezept(dto.getNameRezept(), dto.getAnleitungRezept(), dto.getBild(), dto.getKategorie(), dto.getDauer());
        r.setOwner(user);
        Rezept saved = rezeptRepository.save(r);
        return new RezeptDTO(saved.getId(), saved.getNameRezept(), saved.getAnleitungRezept(), saved.getBild(), saved.getKategorie(), saved.getDauer(), user.getId());
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
        return new RezeptDTO(updated.getId(), updated.getNameRezept(), updated.getAnleitungRezept(), updated.getBild(), updated.getKategorie(), updated.getDauer(), r.getOwner().getId());
    }
}