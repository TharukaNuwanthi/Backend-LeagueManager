package com.example.leaguemanager.model;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {
    private int winCount = 0;
    private int drawCount = 0;
    private int defeatsCount = 0;
    private int goalsCount = 0;
    private int score = 0;
    private double points = 0.0;
    private int numOfMatches = 0;

    public FootballClub(String clubName, String clubLocation, int numOfMembers) {
        super(clubName, clubLocation, numOfMembers);
    }

    public FootballClub(int winCount, int drawCount, int defeatsCount, int goalsCount, int score, double points, int numOfMatches) {
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.defeatsCount = defeatsCount;
        this.goalsCount = goalsCount;
        this.score = score;
        this.points = points;
        this.numOfMatches = numOfMatches;
    }

    public FootballClub() {
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public int getDefeatsCount() {
        return defeatsCount;
    }

    public void setDefeatsCount(int defeatsCount) {
        this.defeatsCount = defeatsCount;
    }

    public int getGoalsCount() {
        return goalsCount;
    }

    public void setGoalsCount(int goalsCount) {
        this.goalsCount = goalsCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getNumOfMatches() {
        return numOfMatches;
    }

    public void setNumOfMatches(int numOfMatches) {
        this.numOfMatches = numOfMatches;
    }

    public String toString() {
        return getClubName() + '\n' +
                ", Club Location  = " + getClubLocation() + '\n' +
                ", Num of Members = " + getNumOfMembers() + '\n' +
                ", Num of Wins    = " + getWinCount() + '\n' +
                ", Num of Draws   = " + getDrawCount() + '\n' +
                ", Num of Defeats = " + getDefeatsCount() + '\n' +
                ", Num of Goals   = " + getGoalsCount() + '\n' +
                ", Num of Score   = " + getScore() + '\n' +
                ", Num of Points  = " + getPoints() + '\n' +
                ", Num of Matches = " + getNumOfMatches() + '\n';
    }

    @Override
    public int compareTo(FootballClub o) {
        return (this.goalsCount - o.goalsCount);
    }
}


