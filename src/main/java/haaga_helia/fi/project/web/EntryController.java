package haaga_helia.fi.project.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import haaga_helia.fi.project.domain.CategoryRepository;
import haaga_helia.fi.project.domain.EntryRepository;
import haaga_helia.fi.project.domain.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EntryController {
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String frontPage(Model model) {
        List<Entry> entries = entryRepository.findAll();
        List<Entry> top5entries = entryRepository.findTop5ByOrderByDateDesc();
        model.addAttribute("entries", entries);
        Long score1 = entries.stream()
                .filter(entry -> entry.getWinner() == Entry.Winner.zerogero)
                .count();

        Long score2 = entries.stream()
                .filter(entry -> entry.getWinner() == Entry.Winner.Bnnanna)
                .count();
        model.addAttribute("zerogeroScore", score1);
        model.addAttribute("bnnannaScore", score2);
        model.addAttribute("top5", top5entries);
        model.addAttribute("categories", categoryRepository.findAll());
        return "homepage";
    }
    @GetMapping("/addentry")
    public String addEntry(Model model) {
        model.addAttribute("entry", new Entry());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addentry";
    }
    @PostMapping("/saveentry")
    public String postMethodName(@ModelAttribute Entry entry) {
        entryRepository.save(entry);
        return "redirect:/";
    }
    @PostMapping("/deleteentry/{id}")
    public String deleteEntry(@PathVariable Long id) {
        entryRepository.deleteById(id);
        return "redirect:/";
    }
    
    @GetMapping("/user/{username}")
    public String viewUserEntries(@PathVariable String username, Model model) {
        Entry.Winner winner;
        try {
            winner = Entry.Winner.valueOf(username);
        } catch (IllegalArgumentException e) {
            return "redirect:/"; 
        }
        List<Entry> entries = entryRepository.findByWinnerOrderByDateDesc(winner);
        model.addAttribute("entries", entries);
        model.addAttribute("username", username);
        return "userpage";
    }
    
    @PostMapping("/deleteentryfromuser/{id}")
    public String deleteEntryFromUser(@PathVariable Long id, @RequestParam String username) {
        entryRepository.deleteById(id);
        return "redirect:/user/" + username;
    }
    
    

    
    
    

    
}
