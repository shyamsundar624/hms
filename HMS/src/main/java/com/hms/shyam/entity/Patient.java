package com.hms.shyam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String age;
	private String blood;
	private String prescription;
	private String dose;
	private String fees;
	private String urgency;
}
