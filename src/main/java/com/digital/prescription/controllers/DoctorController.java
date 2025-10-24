package com.digital.prescription.controllers;

import com.digital.prescription.entities.Doctor;
import com.digital.prescription.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @PreAuthorize("hasAuthority('DOCTOR_CREATE')")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
}
