package ccn.elkadiri.conferenceservice.service.impl;

import ccn.elkadiri.conferenceservice.dto.ConferenceRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ConferenceResponseDTO;
import ccn.elkadiri.conferenceservice.entity.Conference;
import ccn.elkadiri.conferenceservice.enums.ConferenceType;
import ccn.elkadiri.conferenceservice.exception.NotFoundException;
import ccn.elkadiri.conferenceservice.mapper.ConferenceMapper;
import ccn.elkadiri.conferenceservice.repository.ConferenceRepository;
import ccn.elkadiri.conferenceservice.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConferenceServiceImpl implements ConferenceService {
    
    private final ConferenceRepository conferenceRepository;
    private final ConferenceMapper conferenceMapper;
    
    @Override
    public ConferenceResponseDTO createConference(ConferenceRequestDTO requestDTO) {
        log.info("Création d'une nouvelle conférence: {}", requestDTO.getTitre());
        Conference conference = conferenceMapper.toEntity(requestDTO);
        Conference savedConference = conferenceRepository.save(conference);
        return conferenceMapper.toResponseDTO(savedConference);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ConferenceResponseDTO getConferenceById(Long id) {
        log.info("Récupération de la conférence avec l'ID: {}", id);
        Conference conference = conferenceRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Conférence avec l'ID " + id + " non trouvée"));
        return conferenceMapper.toResponseDTO(conference);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ConferenceResponseDTO> getAllConferences() {
        log.info("Récupération de toutes les conférences");
        return conferenceRepository.findAll().stream()
            .map(conferenceMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ConferenceResponseDTO> getConferencesByType(ConferenceType type) {
        log.info("Récupération des conférences de type: {}", type);
        return conferenceRepository.findByType(type).stream()
            .map(conferenceMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO requestDTO) {
        log.info("Mise à jour de la conférence avec l'ID: {}", id);
        Conference conference = conferenceRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Conférence avec l'ID " + id + " non trouvée"));
        conferenceMapper.updateEntity(conference, requestDTO);
        Conference updatedConference = conferenceRepository.save(conference);
        return conferenceMapper.toResponseDTO(updatedConference);
    }
    
    @Override
    public void deleteConference(Long id) {
        log.info("Suppression de la conférence avec l'ID: {}", id);
        if (!conferenceRepository.existsById(id)) {
            throw new NotFoundException("Conférence avec l'ID " + id + " non trouvée");
        }
        conferenceRepository.deleteById(id);
    }
}
