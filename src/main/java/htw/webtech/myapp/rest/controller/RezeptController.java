package htw.webtech.myapp.rest.controller;

import htw.webtech.myapp.business.service.RezeptService;
import htw.webtech.myapp.rest.model.RezeptDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RezeptController {

    private final RezeptService rezeptService;

    public RezeptController(RezeptService rezeptService) {
        this.rezeptService = rezeptService;
    }

    @GetMapping("/rezepte")
    public ResponseEntity<List<RezeptDTO>> getRezept() {
        return ResponseEntity.ok(rezeptService.getAllRezept());
    }


    @PostMapping("/rezepte")
    public ResponseEntity<RezeptDTO> createRezept(@RequestBody RezeptDTO rezept) {
        RezeptDTO createdRezept = rezeptService.createRezept(rezept);
        return ResponseEntity.ok(createdRezept);
    }
}
