package ccn.elkadiri.conferenceservice.mapper;

import ccn.elkadiri.conferenceservice.dto.ConferenceRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ConferenceResponseDTO;
import ccn.elkadiri.conferenceservice.entity.Conference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConferenceMapper {
    
    private final ReviewMapper reviewMapper;
    
    public Conference toEntity(ConferenceRequestDTO dto) {
        Conference conference = new Conference();
        conference.setTitre(dto.getTitre());
        conference.setType(dto.getType());
        conference.setDate(dto.getDate());
        conference.setDuree(dto.getDuree());
        conference.setNombreInscrits(dto.getNombreInscrits());
        conference.setScore(dto.getScore());
        return conference;
    }
    
    public ConferenceResponseDTO toResponseDTO(Conference conference) {
        return new ConferenceResponseDTO(
            conference.getId(),
            conference.getTitre(),
            conference.getType(),
            conference.getDate(),
            conference.getDuree(),
            conference.getNombreInscrits(),
            conference.getScore(),
            conference.getReviews().stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList())
        );
    }
    
    public void updateEntity(Conference conference, ConferenceRequestDTO dto) {
        conference.setTitre(dto.getTitre());
        conference.setType(dto.getType());
        conference.setDate(dto.getDate());
        conference.setDuree(dto.getDuree());
        conference.setNombreInscrits(dto.getNombreInscrits());
        conference.setScore(dto.getScore());
    }
}
