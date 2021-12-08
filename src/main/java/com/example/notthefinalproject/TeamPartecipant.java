package com.example.notthefinalproject;


import java.util.ArrayList;

public class TeamPartecipant extends  Partecipant{
    ArrayList<Student> teamMumbers = new ArrayList<>();
    String teamName;
    int count;
    TeamPartecipant(String teamName, String rank){
        super(rank);
        this.teamName = teamName;
    }
    public void addMumber(Student st){
        teamMumbers.add(st);
        count++;
    }

    @Override
    public String toString() {
        return this.teamName;
    }
}
