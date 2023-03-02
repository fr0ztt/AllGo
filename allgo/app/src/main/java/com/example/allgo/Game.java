package com.example.allgo;

import java.io.Serializable;

public class Game implements Serializable{

    String id;
    String team1;
    String team2;
    String date;
    String competition;
    String resultTeam1;
    String resultTeam2;

    public Game(String id, String team1, String team2, String date, String competition, String resultTeam1,String resultTeam2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.competition=competition;
        this.resultTeam1=resultTeam1;
        this.resultTeam2=resultTeam2;
    }

    public Game() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getResultTeam1() {
        return resultTeam1;
    }

    public void setResultTeam1(String resultTeam1) {
        this.resultTeam1 = resultTeam1;
    }

    public String getResultTeam2() {
        return resultTeam2;
    }

    public void setResultTeam2(String resultTeam2) {
        this.resultTeam2 = resultTeam2;
    }
}
