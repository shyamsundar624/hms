package com.hms.shyam.doclogin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.hms.shyam.doclogin.entity.Medicine;
import com.hms.shyam.doclogin.repository.MedicineRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {

	private final MedicineRepository medicineRepository;
	
	@PostMapping("/insert")
	public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine){
		return ResponseEntity.ok().body(medicineRepository.save(medicine));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Medicine>> getAllMedicine(){
		return ResponseEntity.ok().body(medicineRepository.findAll());
	}
	
	@PutMapping("/medicine/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable long id,@RequestBody Medicine medicineObj){
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new RuntimeException("Medicine Not Found with Id : "+id));
		
		medicine.setDrugName(medicineObj.getDrugName());
		medicine.setStock(medicineObj.getStock());
		
		medicineRepository.save(medicine);
		return ResponseEntity.ok().body(medicine);
	}
	
	@GetMapping("/medicine/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id){
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new RuntimeException("Medicine Not Found By Id "+id));
		
		return ResponseEntity.ok().body(medicine);
	}
	
	@DeleteMapping("/medicine/{id}")
	public ResponseEntity<Map<String, Boolean>>deleteMedicine(@PathVariable Long id){
		Medicine medicine = medicineRepository.findById(id).orElseThrow(()->new RuntimeException("Medicine Not Found Exception "+id));
		medicineRepository.delete(medicine);
		Map<String, Boolean> response=new HashMap<>();
		response.put("Delet", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
		
	}
}
