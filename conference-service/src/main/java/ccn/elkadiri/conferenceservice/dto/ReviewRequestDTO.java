package ccn.elkadiri.conferenceservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    @NotNull(message = "La date est obligatoire")
    private LocalDate date;
    
    @NotBlank(message = "Le texte est obligatoire")
    private String texte;
    
    @NotNull(message = "Le nombre d'étoiles est obligatoire")
    @Min(value = 1, message = "Le nombre d'étoiles doit être entre 1 et 5")
    @Max(value = 5, message = "Le nombre d'étoiles doit être entre 1 et 5")
    private Integer stars;
    
    @NotNull(message = "L'ID de la conférence est obligatoire")
    private Long conferenceId;
}
