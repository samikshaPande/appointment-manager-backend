package com.sam.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sam.model.Appointment;
import com.sam.model.Doctor;
import com.sam.repository.AppointmentRepository;
import com.sam.repository.DoctorRepository;
import com.sam.utils.ResourceNotFoundException;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@GetMapping
	public List<Appointment> getAppointments(@RequestParam String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime = LocalDate.parse(date, formatter);
		return appointmentRepository.getAppointmentsByDate(dateTime);
	}

	@PostMapping
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		Appointment savedAppointment = appointmentRepository.save(appointment);
		return ResponseEntity.ok(savedAppointment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long appointmentId,
			@RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
		appointment.setTitle(appointmentDetails.getTitle());
		appointment.setStartTime(appointmentDetails.getStartTime());
		appointment.setEndTime(appointmentDetails.getEndTime());
		appointment.setClientName(appointmentDetails.getClientName());
		appointment.setDoctor(appointmentDetails.getDoctor());
		Appointment updatedAppointment = appointmentRepository.save(appointment);
		return ResponseEntity.ok(updatedAppointment);
	}

	@PutMapping("/update/status/{id}/{status}")
	public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable Long id, @PathVariable int status)
			throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
		appointment.setStatus(status);
		Appointment updatedAppointment = appointmentRepository.save(appointment);
		return ResponseEntity.ok(updatedAppointment);
	}

	@GetMapping("/available/doctors")
	public List<String> getFreeDoctors(
			@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
			@RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
		List<String> doctorList = appointmentRepository.findFreeDoctorIds(startTime, endTime);
		return doctorList;
	}

	@GetMapping("/doctors")
	public List<Doctor> getDoctorsList() {
		List<Doctor> doctorList = doctorRepository.findAll();
		return doctorList;
	}
}
