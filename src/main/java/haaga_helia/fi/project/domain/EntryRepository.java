package haaga_helia.fi.project.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long>{
    List<Entry> findTop5ByOrderByDateDesc();
    List<Entry> findByWinnerOrderByDateDesc(Entry.Winner winner);
}
