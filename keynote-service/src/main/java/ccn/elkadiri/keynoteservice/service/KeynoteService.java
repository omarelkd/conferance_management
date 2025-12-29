package ccn.elkadiri.keynoteservice.service;

import ccn.elkadiri.keynoteservice.dto.KeynoteRequestDTO;
import ccn.elkadiri.keynoteservice.dto.KeynoteResponseDTO;

import java.util.List;

public interface KeynoteService {
    KeynoteResponseDTO createKeynote(KeynoteRequestDTO requestDTO);
    KeynoteResponseDTO getKeynoteById(Long id);
    List<KeynoteResponseDTO> getAllKeynotes();
    KeynoteResponseDTO updateKeynote(Long id, KeynoteRequestDTO requestDTO);
    void deleteKeynote(Long id);
}
