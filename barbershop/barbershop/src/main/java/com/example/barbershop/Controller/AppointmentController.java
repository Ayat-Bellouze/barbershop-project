package com.example.barbershop.Controller;

import com.example.barbershop.model.*;
import com.example.barbershop.repository.*;
import com.example.barbershop.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/book-appointment")
    public String bookAppointment(
            @RequestParam("date") String dateStr,
            @RequestParam("time") String time,
            @RequestParam("serviceIds[]") List<Long> serviceIds,
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            Model model
    ) {
        System.out.println("Date : " + dateStr);
        System.out.println("Time : " + time);
        System.out.println("Service IDs : " + serviceIds);
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Name : " + name);
        System.out.println("Phone : " + phone);

        // Parse the date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(dateStr, dateFormatter);

        // Convert the time format from HH.MM to HH:mm
        time = time.replace('.', ':');
        try {
            // Parse the time
            LocalTime startTime = LocalTime.parse(time);

            // Book the appointment
            appointmentService.bookAppointment(date, startTime, serviceIds, employeeId, name, phone);
            model.addAttribute("successMessage", "Appointment booked successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "index";
    }

}
