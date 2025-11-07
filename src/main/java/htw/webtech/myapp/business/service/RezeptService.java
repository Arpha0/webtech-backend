package htw.webtech.myapp.business.service;

import htw.webtech.myapp.rest.model.RezeptDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezeptService {

    public List<RezeptDTO> getAllRezept() {
        return List.of(
                new RezeptDTO(1, "Apfelkuchen","test1"),
                new RezeptDTO(2, "Kirschkuchen", "test2")
        );
    }
}
