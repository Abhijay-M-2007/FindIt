package com.findit.findit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;

    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/report-lost")
    public String reportLost() {
        return "report-lost";
    }

    @GetMapping("/report-found")
    public String reportFound() {
        return "report-found";
    }

    @GetMapping("/reported-items")
    public String reportedItems(Model model) {

        model.addAttribute("items", itemRepository.findAll());

        return "reported-items";
    }

    @PostMapping("/submit-item")
    public String submitItem(@ModelAttribute Item item) {

        item.setStatus("OPEN");
        itemRepository.save(item);

        return "redirect:/reported-items";
    }


}
