package com.digital.prescription.service;

import com.digital.prescription.entities.Doctor;
import com.digital.prescription.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepo;

    public Doctor addDoctor(Doctor doctor){
        Doctor savedDoctor = doctorRepo.save(doctor);
        return savedDoctor;
    }
}
