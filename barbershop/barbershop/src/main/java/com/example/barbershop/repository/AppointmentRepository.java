package com.example.barbershop.repository;

import com.example.barbershop.model.Appointment;
import com.example.barbershop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
    // Méthode pour trouver les rendez-vous d'un employé pendant un créneau horaire donné
    List<Appointment> findByEmployeeAndDate(Employee employee, LocalDate date);
}
