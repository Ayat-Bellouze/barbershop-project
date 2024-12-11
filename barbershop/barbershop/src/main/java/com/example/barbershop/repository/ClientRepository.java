package com.example.barbershop.repository;
import com.example.barbershop.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findByPhone(String phone);
}
