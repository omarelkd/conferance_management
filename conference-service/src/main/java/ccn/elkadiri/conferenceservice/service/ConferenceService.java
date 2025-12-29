package ccn.elkadiri.conferenceservice.service;

import ccn.elkadiri.conferenceservice.dto.ConferenceRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ConferenceResponseDTO;
import ccn.elkadiri.conferenceservice.enums.ConferenceType;

import java.util.List;

public interface ConferenceService {
    ConferenceResponseDTO createConference(ConferenceRequestDTO requestDTO);
    ConferenceResponseDTO getConferenceById(Long id);
    List<ConferenceResponseDTO> getAllConferences();
    List<ConferenceResponseDTO> getConferencesByType(ConferenceType type);
    ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO requestDTO);
    void deleteConference(Long id);
}
