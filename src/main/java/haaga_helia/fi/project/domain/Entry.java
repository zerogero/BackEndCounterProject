package haaga_helia.fi.project.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "entries")
@Getter
@Setter
@AllArgsConstructor
public class Entry {
    public enum Winner{
        Bnnanna,
        zerogero
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "Description is required")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Winner winner; //chicken dinner

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;
    
    public Entry() {
        this.date = LocalDate.now();
    }
}
