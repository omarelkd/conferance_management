package ccn.elkadiri.conferenceservice.controller;

import ccn.elkadiri.conferenceservice.dto.ReviewRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ReviewResponseDTO;
import ccn.elkadiri.conferenceservice.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "API de gestion des reviews")
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @PostMapping
    @Operation(summary = "Créer une nouvelle review")
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO requestDTO) {
        ReviewResponseDTO response = reviewService.createReview(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une review par son ID")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long id) {
        ReviewResponseDTO response = reviewService.getReviewById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Récupérer toutes les reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(
            @RequestParam(required = false) Long conferenceId) {
        List<ReviewResponseDTO> response = conferenceId != null
            ? reviewService.getReviewsByConferenceId(conferenceId)
            : reviewService.getAllReviews();
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une review")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO requestDTO) {
        ReviewResponseDTO response = reviewService.updateReview(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une review")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
