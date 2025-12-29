package ccn.elkadiri.conferenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private LocalDate date;
    private String texte;
    private Integer stars;
    private Long conferenceId;
}
