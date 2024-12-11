package com.example.barbershop.Controller;

import com.example.barbershop.model.Service;
import com.example.barbershop.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    /**
     * Endpoint pour obtenir tous les services disponibles
     *
     * @return Liste des services
     */
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
