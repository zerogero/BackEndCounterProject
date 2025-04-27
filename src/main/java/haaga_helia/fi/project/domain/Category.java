package haaga_helia.fi.project.domain;

import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "Category name is required")
    private String name;

    @Column(length = 255)
    private String description;
    
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, orphanRemoval = false)
    List<Entry> entries;
}
