package ccn.elkadiri.conferenceservice.dto;

import ccn.elkadiri.conferenceservice.enums.ConferenceType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRequestDTO {
    @NotBlank(message = "Le titre est obligatoire")
    private String titre;
    
    @NotNull(message = "Le type est obligatoire")
    private ConferenceType type;
    
    @NotNull(message = "La date est obligatoire")
    @FutureOrPresent(message = "La date doit être dans le futur ou aujourd'hui")
    private LocalDate date;
    
    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être au moins de 1 minute")
    private Integer duree;
    
    @NotNull(message = "Le nombre d'inscrits est obligatoire")
    @Min(value = 0, message = "Le nombre d'inscrits doit être positif")
    private Integer nombreInscrits;
    
    @NotNull(message = "Le score est obligatoire")
    @DecimalMin(value = "0.0", message = "Le score doit être au moins 0.0")
    @DecimalMax(value = "5.0", message = "Le score doit être au maximum 5.0")
    private Double score;
}
