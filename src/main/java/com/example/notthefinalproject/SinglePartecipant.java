package com.example.notthefinalproject;


public class SinglePartecipant extends  Partecipant{
    Student partecipant;


    SinglePartecipant(Student partecipant, String rank){
        super(rank);
        this.partecipant = partecipant;
    }


    @Override
    public String toString() {
        return partecipant.name;
    }
}
