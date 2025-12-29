package ccn.elkadiri.conferenceservice.controller;

import ccn.elkadiri.conferenceservice.dto.KeynoteDTO;
import ccn.elkadiri.conferenceservice.feign.KeynoteFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Keynote Proxy", description = "Proxy vers le service keynote")
public class KeynoteProxyController {
    
    private final KeynoteFeignClient keynoteFeignClient;
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un keynote par son ID via Feign")
    @CircuitBreaker(name = "keynoteService", fallbackMethod = "getKeynoteFallback")
    public ResponseEntity<KeynoteDTO> getKeynoteById(@PathVariable Long id) {
        log.info("Appel Feign vers keynote-service pour l'ID: {}", id);
        KeynoteDTO keynote = keynoteFeignClient.getKeynoteById(id);
        return ResponseEntity.ok(keynote);
    }
    
    @GetMapping
    @Operation(summary = "Récupérer tous les keynotes via Feign")
    @CircuitBreaker(name = "keynoteService", fallbackMethod = "getAllKeynotesFallback")
    public ResponseEntity<List<KeynoteDTO>> getAllKeynotes() {
        log.info("Appel Feign vers keynote-service pour récupérer tous les keynotes");
        List<KeynoteDTO> keynotes = keynoteFeignClient.getAllKeynotes();
        return ResponseEntity.ok(keynotes);
    }
    
    public ResponseEntity<KeynoteDTO> getKeynoteFallback(Long id, Exception ex) {
        log.error("Circuit breaker activé pour getKeynoteById: {}", ex.getMessage());
        return ResponseEntity.ok(new KeynoteDTO(null, "Service", "Indisponible", "N/A", "N/A"));
    }
    
    public ResponseEntity<List<KeynoteDTO>> getAllKeynotesFallback(Exception ex) {
        log.error("Circuit breaker activé pour getAllKeynotes: {}", ex.getMessage());
        return ResponseEntity.ok(Collections.emptyList());
    }
}
