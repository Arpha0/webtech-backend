package htw.webtech.myapp;

import htw.webtech.myapp.business.service.RezeptService;
import htw.webtech.myapp.persistence.*;
import htw.webtech.myapp.rest.model.RezeptDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RezeptServiceTest {

    @Mock
    private RezeptRepository rezeptRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RezeptService service;

    @Test
    @DisplayName("Test 1: Rezept erstellen (DTO Logik)")
    void testCreateRezept() {
        // GIVEN
        RezeptDTO inputDto = new RezeptDTO(null, "Pasta", "Kochen", "bild.jpg", "Hauptgericht", "20min", 1L);
        User mockUser = new User();
        ReflectionTestUtils.setField(mockUser, "id", 1L);

        Rezept savedRezept = new Rezept("Pasta", "Kochen", "bild.jpg", "Hauptgericht", "20min");
        ReflectionTestUtils.setField(savedRezept, "id", 100L);
        savedRezept.setOwner(mockUser);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(rezeptRepository.save(any(Rezept.class))).thenReturn(savedRezept);

        // WHEN
        RezeptDTO result = service.createRezept(inputDto);

        // THEN
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Pasta", result.getNameRezept());
        assertEquals(1L, result.getUserId());
    }

    @Test
    @DisplayName("Test 2: Alle Rezepte abrufen")
    void testGetAllRezept() {
        // GIVEN
        Rezept r = new Rezept("Pizza", "Backen", "b.jpg", "Snack", "15min");
        ReflectionTestUtils.setField(r, "id", 1L);
        when(rezeptRepository.findAll()).thenReturn(List.of(r));

        // WHEN
        List<RezeptDTO> results = service.getAllRezept();

        // THEN
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Pizza", results.get(0).getNameRezept());
    }

    @Test
    @DisplayName("Test 3: Rezept löschen")
    void testDeleteRezept() {
        // WHEN
        service.deleteRezept(1L);

        // THEN
        verify(rezeptRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test 4: Rezept aktualisieren")
    void testUpdateRezept() {
        // GIVEN
        Long id = 1L;
        RezeptDTO updateDto = new RezeptDTO(id, "Neu", "Neu", "n.jpg", "Dessert", "5min", 1L);

        User owner = new User();
        ReflectionTestUtils.setField(owner, "id", 1L);

        Rezept r = new Rezept("Alt", "Alt", "a.jpg", "Snack", "10min");
        ReflectionTestUtils.setField(r, "id", id);
        r.setOwner(owner);

        when(rezeptRepository.findById(id)).thenReturn(Optional.of(r));
        when(rezeptRepository.save(any(Rezept.class))).thenReturn(r);

        // WHEN
        RezeptDTO result = service.updateRezept(id, updateDto);

        // THEN
        assertEquals("Neu", result.getNameRezept());
        verify(rezeptRepository).save(any(Rezept.class));
    }

    @Test
    @DisplayName("Test 5: Mapping-Check Owner-ID")
    void testOwnerMapping() {
        // GIVEN
        Rezept r = new Rezept();
        ReflectionTestUtils.setField(r, "id", 5L);
        // Kein Owner gesetzt -> Sollte im DTO null sein
        when(rezeptRepository.findAll()).thenReturn(List.of(r));

        // WHEN
        List<RezeptDTO> results = service.getAllRezept();

        // THEN
        assertNull(results.get(0).getUserId());
    }

}