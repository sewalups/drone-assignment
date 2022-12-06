package com.sewalusteven.droneproject.domains.medications;

public class Medication {
    private  int Id;
    private String name;
    private int weight;
    private String code;
    private String imagePath;

    public Medication(int id, String name, int weight, String code, String imagePath) {
        Id = id;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.imagePath = imagePath;
    }

    public Medication() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
