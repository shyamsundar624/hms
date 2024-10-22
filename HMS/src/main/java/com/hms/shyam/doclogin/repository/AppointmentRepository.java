package com.hms.shyam.doclogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.shyam.doclogin.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

}
