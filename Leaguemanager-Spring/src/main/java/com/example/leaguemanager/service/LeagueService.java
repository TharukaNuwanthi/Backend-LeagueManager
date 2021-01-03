package com.example.leaguemanager.service;

import com.example.leaguemanager.model.FootballClub;
import com.example.leaguemanager.model.Match;

import java.util.List;

public interface LeagueService {
    List<FootballClub> getLeaguePointsTable();
    List<FootballClub> readFootBallClubDetailsFromFile();
    List<Match> readMatchDetailsFromFile();
    Match checkMatchScore(FootballClub footballClub01, FootballClub footballClub02);
    void saveMatchDataInFile(List<Match> matchList);
    void saveClubDetailsInFile(List<FootballClub> clubList);
    List<Match> findMatchByDate(String date);
    Match generateMatch();
}
