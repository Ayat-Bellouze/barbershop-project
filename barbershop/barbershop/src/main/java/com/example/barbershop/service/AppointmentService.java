// Service
package com.example.barbershop.service;

import com.example.barbershop.model.*;
import com.example.barbershop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void bookAppointment(LocalDate date, LocalTime time, List<Long> serviceIds, Long employeeId, String name, String phone) {
        Client client = clientRepository.findByPhone(phone).orElseGet(() -> {
            Client newClient = new Client();
            newClient.setName(name);
            newClient.setPhone(phone);
            return clientRepository.save(newClient);
        });

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        List<Appointment> existingAppointments = appointmentRepository.findByEmployeeAndDate(employee, date);

        int totalDuration = serviceIds.stream()
                .map(serviceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .mapToInt(com.example.barbershop.model.Service::getDuration)
                .sum();

        double totalPrice = serviceIds.stream()
                .map(serviceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .mapToDouble(com.example.barbershop.model.Service::getPrice)
                .sum();

        LocalTime endTime = time.plusMinutes(totalDuration);
        validateAppointment(existingAppointments, time, endTime);

        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setStartTime(time);
        appointment.setEndTime(endTime);
        appointment.setTotalPrice(totalPrice);
        appointment.setClient(client);
        appointment.setEmployee(employee);

        appointment = appointmentRepository.save(appointment);

        for (Long serviceId : serviceIds) {
            AppointmentDetails details = new AppointmentDetails();
            details.setAppointment(appointment);
            details.setService(serviceRepository.findById(serviceId).orElseThrow(() -> new IllegalArgumentException("Invalid service ID")));
            appointmentDetailsRepository.save(details);
        }
    }

    private void validateAppointment(List<Appointment> existingAppointments, LocalTime startTime, LocalTime endTime) {
        for (Appointment existing : existingAppointments) {
            if (!(endTime.isBefore(existing.getStartTime()) || startTime.isAfter(existing.getEndTime()))) {
                throw new IllegalArgumentException("Unfortunately, the selected time slot is already booked. Please choose another available time.");
            }
        }
    }
}
