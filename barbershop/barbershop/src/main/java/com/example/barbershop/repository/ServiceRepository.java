package com.example.barbershop.repository;
import com.example.barbershop.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long>{

}
