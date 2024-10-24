package com.hms.shyam.doclogin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.shyam.doclogin.entity.Appointment;
import com.hms.shyam.doclogin.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {

	private final AppointmentRepository appointmentRepository;

	@PostMapping("/insert")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		return ResponseEntity.ok().body(appointmentRepository.save(appointment));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Appointment>> getAllAppoinments() {
		return ResponseEntity.ok(appointmentRepository.findAll());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable Long id)
			throws AttributeNotFoundException {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Appointment Not Found With Id " + id));
		appointmentRepository.delete(appointment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);

		return ResponseEntity.ok().body(response);
	}
}
