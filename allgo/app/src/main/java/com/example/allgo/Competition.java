package com.example.allgo;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Competition implements Serializable {
    String id;
    String name;
    Long numberTeams;
    Long prize;
    String image;
    String  equipas;

    public Competition(String id, String name, Long numberTeams,Long prize,String equipas, String image) {
        this.id = id;
        this.name = name;
        this.numberTeams = numberTeams;
        this.prize=prize;
        this.equipas=equipas;
        this.image=image;
    }

    public Competition() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberTeams() {
        return numberTeams;
    }

    public void setNumberTeams(Long numberTeams) {
        this.numberTeams = numberTeams;
    }

    public Long getPrize() {
        return prize;
    }

    public void setPrize(Long prize) {
        this.prize = prize;
    }

    public String getEquipas() {
        return equipas;
    }

    public void setEquipas(String equipas) {
        this.equipas = equipas;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
