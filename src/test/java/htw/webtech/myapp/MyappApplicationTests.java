package htw.webtech.myapp;

import htw.webtech.myapp.persistence.Rezept;
import htw.webtech.myapp.rest.model.RezeptDTO;
import htw.webtech.myapp.business.service.RezeptService;
import htw.webtech.myapp.persistence.RezeptRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RezeptService service;

	// simulieren die Datenbank
	@MockitoBean
	private RezeptRepository repository;

	// --- TEST 1: Context Load ---
	@Test
	void contextLoads() {
	}

	// --- TEST 2: Service Save (Erstellen) ---
	@Test
	@DisplayName("2. Service sollte DTO annehmen und Entity speichern")
	void testServiceSave() {
		// Input: DTO ohne ID (weil neu)
		RezeptDTO inputDto = new RezeptDTO();
		inputDto.setNameRezept("Pasta");
		inputDto.setAnleitungRezept("Kochen");
		inputDto.setKategorie("Hauptgericht");
		inputDto.setDauer("15 Min");

		// Output Mock: Entity mit ID (von Datenbank simuliert)
		Rezept savedEntity = new Rezept();
		savedEntity.setId(1L);
		savedEntity.setNameRezept("Pasta");
		savedEntity.setKategorie("Hauptgericht");

		// Wenn repository.save() gerufen wird, gib savedEntity zurück
		doReturn(savedEntity).when(repository).save(any(Rezept.class));

		// Test ausführen
		RezeptDTO result = service.createRezept(inputDto);

		// Prüfen
		assertEquals(1L, result.getId());
		assertEquals("Pasta", result.getNameRezept());
	}

	// --- TEST 3: Service Find All ---
	@Test
	@DisplayName("3. Service sollte Entities in DTOs umwandeln")
	void testServiceFindAll() {
		// Mock Daten
		Rezept r1 = new Rezept();
		r1.setNameRezept("Tee");
		r1.setKategorie("Getränk");

		doReturn(List.of(r1)).when(repository).findAll();

		// Test
		List<RezeptDTO> result = service.getAllRezept();

		// Check
		assertEquals(1, result.size());
		assertEquals("Tee", result.get(0).getNameRezept());
	}

	// --- TEST 4: API GET ---
	@Test
	@DisplayName("4. API GET sollte Status 200 liefern")
	void testApiGet() throws Exception {
		doReturn(List.of()).when(repository).findAll();

		mockMvc.perform(get("/api/v1/rezepte"))
				.andExpect(status().isOk());
	}

	// --- TEST 5: API POST ---
	@Test
	@DisplayName("5. API POST sollte Status 200 liefern")
	void testApiPost() throws Exception {
		// Mock für das Speichern
		Rezept r = new Rezept();
		r.setId(1L);
		r.setNameRezept("Burger");
		doReturn(r).when(repository).save(any(Rezept.class));

		// JSON Body senden
		String jsonBody = "{\"nameRezept\":\"Burger\", \"anleitungRezept\":\"Braten\", \"kategorie\":\"Hauptgericht\", \"dauer\":\"20 Min\"}";

		mockMvc.perform(post("/api/v1/rezepte")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBody))
				.andExpect(status().isOk());
	}

	// --- TEST 6: Service Löschen ---
	@Test
	@DisplayName("6. Service sollte delete im Repo aufrufen")
	void testServiceDelete() {
		Long id = 5L;
		// Wir tun so, als ob das Rezept existiert
		doReturn(true).when(repository).existsById(id);

		service.deleteRezept(id);

		// Wurde deleteById wirklich aufgerufen?
		verify(repository, times(1)).deleteById(id);
	}

	// --- TEST 7: Entity Test ---
	@Test
	@DisplayName("7. Entity Getter/Setter Test")
	void testEntity() {
		Rezept r = new Rezept();
		r.setNameRezept("Test");
		assertEquals("Test", r.getNameRezept());
	}
}