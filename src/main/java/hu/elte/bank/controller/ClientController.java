package hu.elte.bank.controller;

import hu.elte.bank.entity.Client;
import hu.elte.bank.service.ClientService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new Client());
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(){
        clientService.logout( );
        return "logout";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Client client, Model model) {
        if (clientService.isValid(client)) {
            return redirectToGreeting(client);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }
    
    private String redirectToGreeting(@ModelAttribute Client client) {
        return "redirect:/client/greet?name=" + client.getUsername();
    }
}
