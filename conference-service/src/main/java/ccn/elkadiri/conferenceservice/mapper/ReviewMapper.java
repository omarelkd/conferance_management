package ccn.elkadiri.conferenceservice.mapper;

import ccn.elkadiri.conferenceservice.dto.ReviewRequestDTO;
import ccn.elkadiri.conferenceservice.dto.ReviewResponseDTO;
import ccn.elkadiri.conferenceservice.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    
    public Review toEntity(ReviewRequestDTO dto) {
        Review review = new Review();
        review.setDate(dto.getDate());
        review.setTexte(dto.getTexte());
        review.setStars(dto.getStars());
        return review;
    }
    
    public ReviewResponseDTO toResponseDTO(Review review) {
        return new ReviewResponseDTO(
            review.getId(),
            review.getDate(),
            review.getTexte(),
            review.getStars(),
            review.getConference().getId()
        );
    }
    
    public void updateEntity(Review review, ReviewRequestDTO dto) {
        review.setDate(dto.getDate());
        review.setTexte(dto.getTexte());
        review.setStars(dto.getStars());
    }
}
