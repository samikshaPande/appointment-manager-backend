package com.sam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
