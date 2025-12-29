package ccn.elkadiri.conferenceservice.service.impl;

import ccn.elkadiri.conferenceservice.dto.ReviewRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ReviewResponseDTO;
import ccn.elkadiri.conferenceservice.entity.Conference;
import ccn.elkadiri.conferenceservice.entity.Review;
import ccn.elkadiri.conferenceservice.exception.NotFoundException;
import ccn.elkadiri.conferenceservice.mapper.ReviewMapper;
import ccn.elkadiri.conferenceservice.repository.ConferenceRepository;
import ccn.elkadiri.conferenceservice.repository.ReviewRepository;
import ccn.elkadiri.conferenceservice.service.ReviewService;
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
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final ConferenceRepository conferenceRepository;
    private final ReviewMapper reviewMapper;
    
    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO requestDTO) {
        log.info("Création d'une nouvelle review pour la conférence ID: {}", requestDTO.getConferenceId());
        Conference conference = conferenceRepository.findById(requestDTO.getConferenceId())
            .orElseThrow(() -> new NotFoundException("Conférence avec l'ID " + requestDTO.getConferenceId() + " non trouvée"));
        
        Review review = reviewMapper.toEntity(requestDTO);
        review.setConference(conference);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toResponseDTO(savedReview);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ReviewResponseDTO getReviewById(Long id) {
        log.info("Récupération de la review avec l'ID: {}", id);
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Review avec l'ID " + id + " non trouvée"));
        return reviewMapper.toResponseDTO(review);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getAllReviews() {
        log.info("Récupération de toutes les reviews");
        return reviewRepository.findAll().stream()
            .map(reviewMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getReviewsByConferenceId(Long conferenceId) {
        log.info("Récupération des reviews pour la conférence ID: {}", conferenceId);
        return reviewRepository.findByConferenceId(conferenceId).stream()
            .map(reviewMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO requestDTO) {
        log.info("Mise à jour de la review avec l'ID: {}", id);
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Review avec l'ID " + id + " non trouvée"));
        
        if (!review.getConference().getId().equals(requestDTO.getConferenceId())) {
            Conference conference = conferenceRepository.findById(requestDTO.getConferenceId())
                .orElseThrow(() -> new NotFoundException("Conférence avec l'ID " + requestDTO.getConferenceId() + " non trouvée"));
            review.setConference(conference);
        }
        
        reviewMapper.updateEntity(review, requestDTO);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.toResponseDTO(updatedReview);
    }
    
    @Override
    public void deleteReview(Long id) {
        log.info("Suppression de la review avec l'ID: {}", id);
        if (!reviewRepository.existsById(id)) {
            throw new NotFoundException("Review avec l'ID " + id + " non trouvée");
        }
        reviewRepository.deleteById(id);
    }
}
