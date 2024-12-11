package com.example.barbershop.Controller;

import com.example.barbershop.model.Employee;
import com.example.barbershop.model.Service;
import com.example.barbershop.repository.EmployeeRepository;
import com.example.barbershop.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Méthode pour récupérer les services et les employés et les ajouter au modèle
    private void addServicesAndEmployeesToModel(Model model) {
        List<Service> services = serviceRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("services", services);
        model.addAttribute("employees", employees);
    }

    @GetMapping("/")  // La méthode qui gère la page d'accueil
    public String home(Model model) {
        addServicesAndEmployeesToModel(model);  // Utilisation de la méthode commune
        return "index";  // Renvoie la vue index.html
    }

    @GetMapping("/book-appointment")  // La méthode qui gère la page de réservation
    public String bookingpage(Model model) {
        addServicesAndEmployeesToModel(model);  // Utilisation de la méthode commune
        return "index";  // Renvoie la vue index.html
    }
}
