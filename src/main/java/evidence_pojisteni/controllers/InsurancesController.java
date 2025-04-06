package evidence_pojisteni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurances")
public class InsurancesController {

    @GetMapping
    public String renderInsurances() {
        return "/pages/insurances/insurances";
    }
}
