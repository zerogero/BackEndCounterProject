package haaga_helia.fi.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import haaga_helia.fi.project.domain.Comment;
import haaga_helia.fi.project.domain.CommentRepository;
import haaga_helia.fi.project.domain.EntryRepository;
import jakarta.persistence.EntityNotFoundException;
import haaga_helia.fi.project.domain.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private CommentRepository commentRepository;
    
    @GetMapping("/entry/{id}")
    public String viewEntry(@PathVariable Long id, Model model) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entry not found"));
        model.addAttribute("entry", entry);
        model.addAttribute("comments", commentRepository.findByEntry(entry));
        model.addAttribute("newComment", new Comment());
        return "entry";
    }
    @PostMapping("savecomment")
    public String saveComment(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return "redirect:/entry/" + comment.getEntry().getId();
    }
    
}
