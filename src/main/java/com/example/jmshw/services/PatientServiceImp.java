package com.example.jmshw.services;

import com.example.jmshw.domain.Patient;
import com.example.jmshw.repositories.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImp implements PatientService{
    private final PatientRepository patientRepository;

    public PatientServiceImp(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatient(String name) {
        return this.patientRepository.findByName(name);
    }
}
