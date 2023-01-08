package com.example.jmshw.services;

import com.example.jmshw.domain.Patient;
import com.example.jmshw.repositories.PatientRepository;

public interface PatientService {

    Patient getPatient(String name);
}
