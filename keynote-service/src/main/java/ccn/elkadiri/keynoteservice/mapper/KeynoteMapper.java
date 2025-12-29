package ccn.elkadiri.keynoteservice.mapper;

import ccn.elkadiri.keynoteservice.dto.KeynoteRequestDTO;
import ccn.elkadiri.keynoteservice.dto.KeynoteResponseDTO;
import ccn.elkadiri.keynoteservice.entity.Keynote;
import org.springframework.stereotype.Component;

@Component
public class KeynoteMapper {
    
    public Keynote toEntity(KeynoteRequestDTO dto) {
        Keynote keynote = new Keynote();
        keynote.setNom(dto.getNom());
        keynote.setPrenom(dto.getPrenom());
        keynote.setEmail(dto.getEmail());
        keynote.setFonction(dto.getFonction());
        return keynote;
    }
    
    public KeynoteResponseDTO toResponseDTO(Keynote keynote) {
        return new KeynoteResponseDTO(
            keynote.getId(),
            keynote.getNom(),
            keynote.getPrenom(),
            keynote.getEmail(),
            keynote.getFonction()
        );
    }
    
    public void updateEntity(Keynote keynote, KeynoteRequestDTO dto) {
        keynote.setNom(dto.getNom());
        keynote.setPrenom(dto.getPrenom());
        keynote.setEmail(dto.getEmail());
        keynote.setFonction(dto.getFonction());
    }
}
