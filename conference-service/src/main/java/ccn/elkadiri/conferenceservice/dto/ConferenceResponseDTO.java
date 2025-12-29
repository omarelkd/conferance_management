package ccn.elkadiri.conferenceservice.dto;

import ccn.elkadiri.conferenceservice.enums.ConferenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceResponseDTO {
    private Long id;
    private String titre;
    private ConferenceType type;
    private LocalDate date;
    private Integer duree;
    private Integer nombreInscrits;
    private Double score;
    private List<ReviewResponseDTO> reviews;
}
