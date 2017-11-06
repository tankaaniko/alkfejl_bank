package hu.elte.bank.controller;

import hu.elte.bank.entity.Employee;
import hu.elte.bank.service.EmployeeService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new Employee());
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(){
        employeeService.logout();
        return "logout";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Employee employee, Model model) {
        if (employeeService.isValid(employee)) {
            return redirectToGreeting(employee);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }
    
    private String redirectToGreeting(@ModelAttribute Employee employee) {
        return "redirect:/client/greet?name=" + employee.getName();
    }
}
