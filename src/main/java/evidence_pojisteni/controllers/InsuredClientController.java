package evidence_pojisteni.controllers;

import evidence_pojisteni.models.dto.ClientDTO;
import evidence_pojisteni.models.dto.InsuranceDTO;
import evidence_pojisteni.models.dto.mappers.ClientMapper;
import evidence_pojisteni.models.dto.mappers.InsuranceMapper;
import evidence_pojisteni.models.enums.InsuranceType;
import evidence_pojisteni.models.exceptions.ClientNotFoundException;
import evidence_pojisteni.models.exceptions.InsuranceNotFoundException;
import evidence_pojisteni.models.services.ClientService;
import evidence_pojisteni.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/insured-clients")
public class InsuredClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private InsuranceMapper insuranceMapper;

    /**
     * Zobrazí stránku s přehledem všech pojištěných osob.
     * @param model Model pro předání dat do šablony.
     * @return Název šablony s přehledem pojištěných osob.
     */
    @GetMapping
    public String renderClients(Model model) {
        List<ClientDTO> clients = clientService.getAll();
        model.addAttribute("clients", clients);

        return "pages/insured-clients/insured-clients";
    }

    /**
     * Zobrazí stránku s formulářem pro vytvoření pojištěnce.
     * @param client Objekt pro propojení formulářových polí s daty typu ClientDTO.
     * @return Název šablony s formulářem pro vytvoření pojištěnce.
     */
    @GetMapping("/new")
    public String renderNewClientForm(@ModelAttribute("client") ClientDTO client) {
        return "pages/insured-clients/new";
    }

    /**
     * Odesílá a validuje formulář s vyplněnými daty a vytváří pojištěnce.
     * @param client Objekt propojující formulářová pole s objektem typu ClientDTO
     * @param result Výsledek validace vstupu (uchovává případné chyby).
     * @param model Model předávající data zpět do šablony v případě chyb.
     * @param redirectAttributes Atributy pro zobrazení flash zprávy po úspěšném vytvoření.
     * @return Přesměrování zpět na přehled pojištěných, nebo zpět na formulář v případě chyb.
     */
    @PostMapping("/new")
    public String createClient(
            @Valid @ModelAttribute("client") ClientDTO client,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if (result.hasErrors()) {
            model.addAttribute("client", client);
            return "pages/insured-clients/new";
        }

        clientService.create(client);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec vytvořen");

        return "redirect:/insured-clients";
    }

    /**
     * Zobrazí stránku s detailními informacemi o pojištěném a jeho přehled pojištění.
     * @param clientId ID pojištěnce získané z URL adresy.
     * @param model Model pro předání dat do šablony.
     * @return Název šablony s detailem o pojištěném.
     */
    @GetMapping("/{clientId}")
    public String renderDetail(
            @PathVariable long clientId,
            Model model
    ) {
        //Získání dat o konkrétním klientovi podle ID
        ClientDTO client = clientService.getById(clientId);
        //Získání seznamu pojištění daného klienta
        List<InsuranceDTO> insurances = insuranceService.getByClientId(clientId); //List typu InsuranceDTO a jeho naplnění pojištěním daného klienta

        model.addAttribute("insurances", insurances);
        model.addAttribute("client", client);

        return "pages/insured-clients/detail";
    }

    /**
     * Zobrazí stránku s formulářem pro úpravu informací o klientovi
     * @param clientId ID pojištěnce získané z URL adresy
     * @param model Model pro předání dat do formuláře.
     * @return Název šablony s formulářem pro úpravu pojištěnce
     */
    @GetMapping("/edit/{clientId}")
    public String renderEditClientForm(
            @PathVariable Long clientId,
            Model model
    ) {
        ClientDTO fetchedClient = clientService.getById(clientId);
        model.addAttribute("client", fetchedClient);

        return "pages/insured-clients/edit";
    }

    /**
     * Odesílá a validuje formulář s daty pro editaci pojištěnce
     * @param clientId ID pojištěnce získané z URL adresy
     * @param client Objekt propojující formulář s objektem ClientDTO
     * @param result Výsledek validace vstupu (uchovává případné chyby)
     * @param model Model pro předání dat do šablony
     * @param redirectAttributes Atributy pro zobrazení flash zprávy po úspěšném uložení.
     * @return Přesměrování na výpis všech pojištěných, v případě chyb ve vstupu znovuzobrazení formuláře.
     */
    @PostMapping("/edit/{clientId}")
    public String editClient(
            @PathVariable long clientId,
            @Valid @ModelAttribute("client") ClientDTO client,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("client", client);
            return "pages/insured-clients/edit";
        }

        client.setClientId(clientId);
        clientService.edit(client);

        redirectAttributes.addFlashAttribute("success", "Pojištěnec upraven.");
        return "redirect:/insured-clients";
    }

    /**
     * Odstraní pojištěnce s daným ID
     * @param clientId ID pojištěnce získané z URL adresy
     * @param redirectAttributes Atributy pro zobrazení flash zprávy po úspěšném smazání.
     * @return Přesměrování na výpis všech pojištěných.
     */
    @GetMapping("delete/{clientId}")
    public String deleteClient(
            @PathVariable long clientId,
            RedirectAttributes redirectAttributes
    ) {
        if (!insuranceService.getByClientId(clientId).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Pojištěnec má aktivní pojištění. Pro smazání pojištěného je nutné prvně zrušit aktivní pojištění.");
            return "redirect:/insured-clients";
        }
        clientService.delete(clientId);
        
        redirectAttributes.addFlashAttribute("success", "Pojištěnec smazán.");
        return "redirect:/insured-clients";
    }

    /**
     * Zobrazí flash zprávu v případě, že pojištěnec s daným ID nebyl nalezen.
     * @param redirectAttributes Atributy pro zobrazení flash zprávy o nenalezení.
     * @return Přesměrování na výpis všech pojištěných.
     */
    @ExceptionHandler({ClientNotFoundException.class})
    public String handleClientNotFoundException(
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");
        return "redirect:/insured-clients";
    }

    /**
     * Zobrazí flash zprávu v případě, že pojištění s daným ID nebylo nalezeno.
     * @param redirectAttributes Atributy pro zobrazení flash zprávy o nenalezení.
     * @return Přesměrování na výpis všech pojištěných.
     */
    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsurancetNotFoundException(
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("error", "Pojištění nenalezeno.");
        return "redirect:/insured-clients";
    }

    /**
     * Zobrazí stránku s formulářem pro vytvoření nového pojištění.
     * @param clientId ID pojištěnce získané z URL adresy
     * @param model Model pro předání dat do šablony.
     * @return Název šablony s formulářem pro vytvoření nového pojištění.
     */
    @GetMapping("/{clientId}/new-insurance")
    public String renderCreateInsurance(
            @PathVariable long clientId,
            Model model
    ){
        InsuranceDTO dto = new InsuranceDTO();
        dto.setClientId(clientId);
        model.addAttribute("insurance", dto);
        model.addAttribute("insuranceTypes", InsuranceType.values());
        return "pages/insurances/new-insurance";
    }

    /**
     * Odesílá a validuje formulář s daty pro vytvoření nového pojištění
     * @param clientId ID pojištěnce získané z URL adresy
     * @param insurance Objekt propojující data z formuláře s instancí typu InsuranceDTO
     * @param result Výsledek validace vstupu uchovávající případné chyby
     * @param model Model pro naplnění formuláře daty.
     * @param redirectAttributes Atributy pro zobrazení flash zprávy.
     * @return Přesměrování na detail pojištěného, v případě chybného vstupu znovuzobrazení formuláře
     */
    @PostMapping("/{clientId}/new-insurance")
    public String createInsurance(
            @PathVariable long clientId,
            @Valid @ModelAttribute InsuranceDTO insurance,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
            ) {
        if (result.hasErrors()) {
            insurance.setClientId(clientId);
            model.addAttribute("insurance", insurance);
            model.addAttribute("insuranceTypes", InsuranceType.values());
            return "pages/insurances/new-insurance";
        }

        redirectAttributes.addFlashAttribute("success", "Pojištění úspešně přidáno.");
        insuranceService.create(insurance);
        return "redirect:/insured-clients/" + clientId;
    }

    /**
     * Odstraní konkrétní pojištění
     * @param insuranceId ID pojištění získané z URL adresy
     * @param clientId ID pojištěnce získané z URL adresy
     * @param redirectAttributes Atributy pro zobrazení flash zprávy.
     * @return Přesměrování na detail pojištěnce
     */
    @GetMapping("/{clientId}/delete-insurance/{insuranceId}")
    public String deleteInsurance(
            @PathVariable long insuranceId,
            @PathVariable long clientId,
            RedirectAttributes redirectAttributes
    ) {
        insuranceService.delete(insuranceId);

        redirectAttributes.addFlashAttribute("success", "Pojištění úspěšně odstraněno");
        return "redirect:/insured-clients/" + clientId;
    }

    /**
     * Zobrazí stránku s formulářem pro editaci konkrétního pojištění
     * @param clientId ID pojištěnce získané z URL adresy
     * @param insuranceId ID pojištění získané z URL adresy
     * @param model Model pro předání dat do šablony
     * @return Název šablony s formulářem pro editaci pojištění
     */
    @GetMapping("/{clientId}/edit-insurance/{insuranceId}")
    public String renderEditInsurance(
            @PathVariable long clientId,
            @PathVariable long insuranceId,
            Model model
    ) {
        InsuranceDTO fetchedInsurance = insuranceService.getById(insuranceId);
        fetchedInsurance.setClientId(clientId);
        model.addAttribute("insurance", fetchedInsurance);
        model.addAttribute("clientId", clientId);
        model.addAttribute("insuranceTypes", InsuranceType.values());

        return "pages/insurances/edit-insurance";
    }

    /**
     * Odesílá a validuje formulář s editací pojištění
     * @param clientId ID pojištěnce získané z URL adresy
     * @param insuranceId ID pojištění získané z URL adresy
     * @param insurance Objekt propojující data z formuláře s instancí typu InsuranceDTO
     * @param result Výsledek validace vstupu uchovávající případné chyby
     * @param model Model pro naplnění formuláře daty.
     * @param redirectAttributes Atributy pro zobrazení flash zprávy.
     * @return Přesměrování na detail pojištěnce, nebo návrat na formulář v případě chybného vstupu
     */
    @PostMapping("/{clientId}/edit-insurance/{insuranceId}")
    public String editInsurance(
            @PathVariable long clientId,
            @PathVariable long insuranceId,
            @Valid @ModelAttribute("insurance") InsuranceDTO insurance,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            insurance.setClientId(clientId);
            model.addAttribute("insurance", insurance);
            model.addAttribute("clientId", clientId);
            model.addAttribute("insuranceTypes", InsuranceType.values());
            return "pages/insurances/edit-insurance";
        }
        insurance.setClientId(clientId);
        insuranceService.edit(insurance);

        redirectAttributes.addFlashAttribute("success", "Pojištění úspěšně upraveno.");
        return "redirect:/insured-clients/" + clientId;
    }
}
