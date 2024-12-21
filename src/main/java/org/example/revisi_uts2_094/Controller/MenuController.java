package org.example.revisi_uts2_094.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MenuController {
    @GetMapping("/Menu")
    public String menu() {
        return "Menu"; // Maps to Menu.html
    }
}
