package com.sewalusteven.droneproject.domains.loads.readmodel;

import com.sewalusteven.droneproject.domains.drones.readmodel.Drone;
import com.sewalusteven.droneproject.domains.loads.exceptions.MedicationNotFoundInLoad;
import com.sewalusteven.droneproject.domains.medications.readmodel.Medication;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Load {

    @Id
    @SequenceGenerator(name = "load_sequence", sequenceName = "load_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "load_id_sequence")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "drone_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Drone drone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "drone_medications", joinColumns = { @JoinColumn(name = "load_id") }, inverseJoinColumns = { @JoinColumn(name = "medication_id") })
    private Set<Medication> medications = new HashSet<>();
    private LocalDate loadDate;

    public Load(Drone drone, Set<Medication> medications, LocalDate loadDate) {
        this.drone = drone;
        this.medications = medications;
        this.loadDate = loadDate;
    }

    public Load(Integer id, Drone drone, Set<Medication> medications, LocalDate loadDate) {
        this.id = id;
        this.drone = drone;
        this.medications = medications;
        this.loadDate = loadDate;
    }

    public Load() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void removeMedication(Integer medicationId) {
        var medication = this.medications
                .stream()
                .filter(m -> m.getId() == medicationId)
                .findFirst()
                .orElseThrow(()-> new MedicationNotFoundInLoad(medicationId, this.id));

        if (medication != null) {
            this.medications.remove(medication);
        }
    }

    public LocalDate getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(LocalDate loadDate) {
        this.loadDate = loadDate;
    }
}
