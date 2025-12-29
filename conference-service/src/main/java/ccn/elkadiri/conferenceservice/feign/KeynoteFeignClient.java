package ccn.elkadiri.conferenceservice.feign;

import ccn.elkadiri.conferenceservice.dto.KeynoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteFeignClient {
    
    @GetMapping("/api/keynotes/{id}")
    KeynoteDTO getKeynoteById(@PathVariable Long id);
    
    @GetMapping("/api/keynotes")
    List<KeynoteDTO> getAllKeynotes();
}
