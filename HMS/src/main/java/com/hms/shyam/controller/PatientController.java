package com.hms.shyam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.shyam.doclogin.entity.Appointment;
import com.hms.shyam.entity.Patient;
import com.hms.shyam.repository.PatientRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@PostMapping("/insert")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		return ResponseEntity.ok().body(patientRepository.save(patient));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatient(){
		return ResponseEntity.ok(patientRepository.findAll());
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException{
		
		Patient patient = patientRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Patient Not FOund With Id "+ id));
		return ResponseEntity.ok().body(patient);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable Long id)
			throws AttributeNotFoundException {
		Patient appointment = patientRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Appointment Not Found With Id " + id));
		
		patientRepository.delete(appointment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Patient> updatePatientById(@PathVariable Long id, @RequestBody Patient patient) throws AttributeNotFoundException{
		Patient obj = patientRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Patient Not Found With Id " + id));
		
		obj.setAge(patient.getAge());
		obj.setName(patient.getName());
		obj.setBlood(patient.getBlood());
		obj.setDose(patient.getDose());
		obj.setFees(patient.getFees());
		obj.setPrescription(patient.getPrescription());
		obj.setUrgency(patient.getUrgency());
		
		patientRepository.save(obj);
		
		return ResponseEntity.ok().body(obj);
	}

}
