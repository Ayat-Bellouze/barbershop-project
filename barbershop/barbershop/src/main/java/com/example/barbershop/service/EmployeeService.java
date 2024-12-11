package com.example.barbershop.service;

import com.example.barbershop.model.Employee;
import com.example.barbershop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Méthode pour récupérer un employé par son ID
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    // Vous pouvez ajouter d'autres méthodes pour créer, mettre à jour ou supprimer un employé si nécessaire
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
