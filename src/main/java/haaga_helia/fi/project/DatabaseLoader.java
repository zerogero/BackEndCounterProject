package haaga_helia.fi.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import haaga_helia.fi.project.domain.*;

@Configuration
public class DatabaseLoader {
    @Bean
    public CommandLineRunner demo(
        CategoryRepository categoryRepository,
        CommentRepository commentRepository,
        EntryRepository entryRepository

    )
    {return (args) -> {
        // category
        if (categoryRepository.count() == 0) {
            Category dumbass = new Category();
            dumbass.setDescription("Example");
            dumbass.setName("Beating the elderly");
            categoryRepository.save(dumbass);
        }
    };
};

}
