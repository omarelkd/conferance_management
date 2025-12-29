package ccn.elkadiri.keynoteservice.service.impl;

import ccn.elkadiri.keynoteservice.dto.KeynoteRequestDTO;
import ccn.elkadiri.keynoteservice.dto.KeynoteResponseDTO;
import ccn.elkadiri.keynoteservice.entity.Keynote;
import ccn.elkadiri.keynoteservice.exception.NotFoundException;
import ccn.elkadiri.keynoteservice.mapper.KeynoteMapper;
import ccn.elkadiri.keynoteservice.repository.KeynoteRepository;
import ccn.elkadiri.keynoteservice.service.KeynoteService;
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
public class KeynoteServiceImpl implements KeynoteService {
    
    private final KeynoteRepository keynoteRepository;
    private final KeynoteMapper keynoteMapper;
    
    @Override
    public KeynoteResponseDTO createKeynote(KeynoteRequestDTO requestDTO) {
        log.info("Création d'un nouveau keynote: {}", requestDTO.getEmail());
        Keynote keynote = keynoteMapper.toEntity(requestDTO);
        Keynote savedKeynote = keynoteRepository.save(keynote);
        return keynoteMapper.toResponseDTO(savedKeynote);
    }
    
    @Override
    @Transactional(readOnly = true)
    public KeynoteResponseDTO getKeynoteById(Long id) {
        log.info("Récupération du keynote avec l'ID: {}", id);
        Keynote keynote = keynoteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Keynote avec l'ID " + id + " non trouvé"));
        return keynoteMapper.toResponseDTO(keynote);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<KeynoteResponseDTO> getAllKeynotes() {
        log.info("Récupération de tous les keynotes");
        return keynoteRepository.findAll().stream()
            .map(keynoteMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public KeynoteResponseDTO updateKeynote(Long id, KeynoteRequestDTO requestDTO) {
        log.info("Mise à jour du keynote avec l'ID: {}", id);
        Keynote keynote = keynoteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Keynote avec l'ID " + id + " non trouvé"));
        keynoteMapper.updateEntity(keynote, requestDTO);
        Keynote updatedKeynote = keynoteRepository.save(keynote);
        return keynoteMapper.toResponseDTO(updatedKeynote);
    }
    
    @Override
    public void deleteKeynote(Long id) {
        log.info("Suppression du keynote avec l'ID: {}", id);
        if (!keynoteRepository.existsById(id)) {
            throw new NotFoundException("Keynote avec l'ID " + id + " non trouvé");
        }
        keynoteRepository.deleteById(id);
    }
}
