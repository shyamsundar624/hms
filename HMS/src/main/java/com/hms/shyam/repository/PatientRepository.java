package com.hms.shyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.shyam.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{

}
