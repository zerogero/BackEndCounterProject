package haaga_helia.fi.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import haaga_helia.fi.project.domain.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import haaga_helia.fi.project.domain.Category;
import haaga_helia.fi.project.domain.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addcategory")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @GetMapping("/editcategory/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).orElse(null));
        return "editcategory";
    }

    @PostMapping("/savecategory")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }

    @PostMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        for (Entry entry : category.getEntries()) {
            entry.setCategory(null);
        }
        categoryRepository.deleteById(id);
        return "redirect:/";
    }
}
