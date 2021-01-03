package com.example.leaguemanager.model;

import java.io.Serializable;

public class Match implements Serializable {
    private String teamOneName;
    private String teamTwoName;
    private int teamOneScore;
    private int teamTwoScore;
    private String matchDate;
    private int teamOneGoal;
    private int teamTwoGoal;

    public int getTeamOneGoal() {
        return teamOneGoal;
    }

    public void setTeamOneGoal(int teamOneGoal) {
        this.teamOneGoal = teamOneGoal;
    }

    public int getTeamTwoGoal() {
        return teamTwoGoal;
    }

    public void setTeamTwoGoal(int teamTwoGoal) {
        this.teamTwoGoal = teamTwoGoal;
    }

    public String getTeamOneName() {
        return teamOneName;
    }

    public void setTeamOneName(String teamOneName) {
        this.teamOneName = teamOneName;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamTwoName(String teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

}