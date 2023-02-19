package com.sam.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sam.model.Appointment;

import jakarta.transaction.Transactional;

@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, String>{
	
		
//	@Query("SELECT DISTINCT a.doctorId FROM Appointment a WHERE " +
//            "NOT EXISTS (SELECT 1 FROM Appointment b " +
//            "WHERE b.doctorId = a.doctorId AND " +
//            "((b.startTime < :startTime AND b.endTime > :startTime) " +
//            "OR (b.startTime < :endTime AND b.endTime > :endTime) " +
//            "OR (b.startTime > :startTime AND b.endTime < :endTime)))") 
	
	@Query("SELECT DISTINCT a.doctorId FROM Appointment a " +
		       "WHERE NOT EXISTS (" +
		       "  SELECT 1 FROM Appointment a2 WHERE a.doctorId = a2.doctorId AND " +
		       "  (a2.startTime BETWEEN :startTime AND :endTime OR a2.endTime BETWEEN :startTime AND :endTime)" +
		       ")")
    List<String> findFreeDoctorIds(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

	
	List<Appointment> findByDoctorId(Long doctorId);

	Optional<Appointment> findById(Long appointmentId);
	
	@Query("SELECT a FROM Appointment a WHERE DATE(a.startTime) = :date")
	List<Appointment> getAppointmentsByDate(@Param("date") LocalDate date);

}
