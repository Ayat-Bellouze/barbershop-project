package com.example.barbershop.service;

import com.example.barbershop.model.Service;
import com.example.barbershop.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Service> getServicesByIds(List<Long> serviceIds) {
        return serviceRepository.findAllById(serviceIds);
    }
}
