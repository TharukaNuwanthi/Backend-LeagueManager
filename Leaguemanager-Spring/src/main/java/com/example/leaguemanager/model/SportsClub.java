package com.example.leaguemanager.model;

import java.io.Serializable;

public class SportsClub implements Serializable {

    private String clubName;
    private String clubLocation;
    private int numOfMembers;

    public SportsClub() {
    }

    public SportsClub(String clubName) {
        this.clubName = clubName;
    }

    public SportsClub(String clubName, String clubLocation, int numOfMembers) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.numOfMembers = numOfMembers;

    }
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public int getNumOfMembers() {
        return numOfMembers;
    }

    public void setNumOfMembers(int numOfMembers) {
        this.numOfMembers = numOfMembers;
    }

    public String toString() {
        return "SportsClub{" +
                "Club Name        = " + getClubName() + '\'' +
                ", Club Location  = " + getClubLocation() + '\'' +
                ", Num of Members = " + getNumOfMembers() + '\'' +
                '}';
    }
}


