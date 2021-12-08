package com.example.notthefinalproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Competition {


    ArrayList<Partecipant> partecipants = new ArrayList<>();
    String compName;
    String compUrl;
    LocalDate compDate;
    boolean single;

    Competition(String compName, LocalDate compDate, String compUrl, boolean single){
        this.compDate = compDate;
        this.compName = compName;
        this.compUrl = compUrl;
        this.single = single;
    }

    public  void addPartecipant(Partecipant p){
        this.partecipants.add(p);
    }

    @Override
    public String toString() {
        return compName ;
    }
}
