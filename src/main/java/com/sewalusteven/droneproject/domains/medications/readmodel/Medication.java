package com.sewalusteven.droneproject.domains.medications.readmodel;

import jakarta.persistence.*;

@Entity
public class Medication {

    @Id
    @SequenceGenerator(
            name = "medication_sequence",
            sequenceName = "medication_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medication_id_sequence"
    )
    private  Integer Id;
    private String name;
    private int weight;
    private String code;
    private String imagePath;

    public Medication(Integer id, String name, int weight, String code, String imagePath) {
        Id = id;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.imagePath = imagePath;
    }

    public Medication(String name, int weight, String code, String imagePath) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.imagePath = imagePath;
    }

    public Medication() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
