package ccn.elkadiri.conferenceservice.service;

import ccn.elkadiri.conferenceservice.dto.ReviewRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO requestDTO);
    ReviewResponseDTO getReviewById(Long id);
    List<ReviewResponseDTO> getAllReviews();
    List<ReviewResponseDTO> getReviewsByConferenceId(Long conferenceId);
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO requestDTO);
    void deleteReview(Long id);
}
