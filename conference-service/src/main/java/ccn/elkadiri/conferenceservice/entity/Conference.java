package ccn.elkadiri.conferenceservice.entity;

import ccn.elkadiri.conferenceservice.enums.ConferenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConferenceType type;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private Integer duree;
    
    @Column(nullable = false)
    private Integer nombreInscrits;
    
    @Column(nullable = false)
    private Double score;
    
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
