package com.example.jmshw.repositories;

import com.example.jmshw.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {

    Patient findByName(String name);
}
