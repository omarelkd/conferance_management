package ccn.elkadiri.conferenceservice.repository;

import ccn.elkadiri.conferenceservice.entity.Conference;
import ccn.elkadiri.conferenceservice.enums.ConferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    List<Conference> findByType(ConferenceType type);
}
