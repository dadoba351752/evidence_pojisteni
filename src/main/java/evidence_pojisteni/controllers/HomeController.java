package evidence_pojisteni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String renderIndex() {
        return "pages/home/index";
    }

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/home";
    }
}
