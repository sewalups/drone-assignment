package com.sewalusteven.droneproject.domains.medications.readmodel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
