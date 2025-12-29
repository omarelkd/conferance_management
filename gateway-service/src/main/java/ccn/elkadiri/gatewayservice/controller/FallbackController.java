package ccn.elkadiri.gatewayservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    
    @GetMapping("/keynote")
    public ResponseEntity<Map<String, Object>> keynoteFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
        response.put("message", "Le service keynote est temporairement indisponible. Veuillez réessayer plus tard.");
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
    
    @GetMapping("/conference")
    public ResponseEntity<Map<String, Object>> conferenceFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
        response.put("message", "Le service conference est temporairement indisponible. Veuillez réessayer plus tard.");
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
