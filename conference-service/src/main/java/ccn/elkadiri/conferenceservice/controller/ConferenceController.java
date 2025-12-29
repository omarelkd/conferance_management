package ccn.elkadiri.conferenceservice.controller;

import ccn.elkadiri.conferenceservice.dto.ConferenceRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ConferenceResponseDTO;
import ccn.elkadiri.conferenceservice.enums.ConferenceType;
import ccn.elkadiri.conferenceservice.service.ConferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
@Tag(name = "Conference", description = "API de gestion des conférences")
public class ConferenceController {
    
    private final ConferenceService conferenceService;
    
    @PostMapping
    @Operation(summary = "Créer une nouvelle conférence")
    public ResponseEntity<ConferenceResponseDTO> createConference(@Valid @RequestBody ConferenceRequestDTO requestDTO) {
        ConferenceResponseDTO response = conferenceService.createConference(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une conférence par son ID")
    public ResponseEntity<ConferenceResponseDTO> getConferenceById(@PathVariable Long id) {
        ConferenceResponseDTO response = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Récupérer toutes les conférences")
    public ResponseEntity<List<ConferenceResponseDTO>> getAllConferences(
            @RequestParam(required = false) ConferenceType type) {
        List<ConferenceResponseDTO> response = type != null
            ? conferenceService.getConferencesByType(type)
            : conferenceService.getAllConferences();
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une conférence")
    public ResponseEntity<ConferenceResponseDTO> updateConference(
            @PathVariable Long id,
            @Valid @RequestBody ConferenceRequestDTO requestDTO) {
        ConferenceResponseDTO response = conferenceService.updateConference(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une conférence")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }
}
