package com.example.barbershop.repository;

import com.example.barbershop.model.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Long> {
}
