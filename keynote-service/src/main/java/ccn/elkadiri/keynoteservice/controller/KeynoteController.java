package ccn.elkadiri.keynoteservice.controller;

import ccn.elkadiri.keynoteservice.dto.KeynoteRequestDTO;
import ccn.elkadiri.keynoteservice.dto.KeynoteResponseDTO;
import ccn.elkadiri.keynoteservice.service.KeynoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
@RequiredArgsConstructor
@Tag(name = "Keynote", description = "API de gestion des keynotes")
public class KeynoteController {
    
    private final KeynoteService keynoteService;
    
    @PostMapping
    @Operation(summary = "Créer un nouveau keynote")
    public ResponseEntity<KeynoteResponseDTO> createKeynote(@Valid @RequestBody KeynoteRequestDTO requestDTO) {
        KeynoteResponseDTO response = keynoteService.createKeynote(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un keynote par son ID")
    public ResponseEntity<KeynoteResponseDTO> getKeynoteById(@PathVariable Long id) {
        KeynoteResponseDTO response = keynoteService.getKeynoteById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Récupérer tous les keynotes")
    public ResponseEntity<List<KeynoteResponseDTO>> getAllKeynotes() {
        List<KeynoteResponseDTO> response = keynoteService.getAllKeynotes();
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un keynote")
    public ResponseEntity<KeynoteResponseDTO> updateKeynote(
            @PathVariable Long id,
            @Valid @RequestBody KeynoteRequestDTO requestDTO) {
        KeynoteResponseDTO response = keynoteService.updateKeynote(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un keynote")
    public ResponseEntity<Void> deleteKeynote(@PathVariable Long id) {
        keynoteService.deleteKeynote(id);
        return ResponseEntity.noContent().build();
    }
}
