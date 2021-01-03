package com.example.leaguemanager.service.impl;

import com.example.leaguemanager.model.FootballClub;
import com.example.leaguemanager.model.Match;
import com.example.leaguemanager.service.LeagueService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class LeagueServiceImpl implements LeagueService {
    @Override
    public List<FootballClub> getLeaguePointsTable() {
        return this.readFootBallClubDetailsFromFile();
    }

    @Override
    public List<FootballClub> readFootBallClubDetailsFromFile() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<FootballClub>> typeReference = new TypeReference<List<FootballClub>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/footballclub.json");
            List<FootballClub> footballClubs = mapper.readValue(inputStream, typeReference);
            return footballClubs;

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Match> readMatchDetailsFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<Match>> typeReference = new TypeReference<List<Match>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/match.json");
            List<Match> matches = mapper.readValue(inputStream, typeReference);
            return matches;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Match checkMatchScore(FootballClub footballClub01, FootballClub footballClub02) {
        List<FootballClub> footballClubs = readFootBallClubDetailsFromFile();
        List<Match> matchDetails = new ArrayList<>();
        for (FootballClub footballClub : footballClubs) {
            if (footballClub01.getScore() < footballClub02.getScore()) {
                if (footballClub.getClubName().equalsIgnoreCase(footballClub02.getClubName())) {
                    footballClub.setScore(footballClub.getScore() + footballClub02.getScore());
                    footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                    footballClub.setWinCount(footballClub.getWinCount() + 1);
                    footballClub.setGoalsCount(footballClub.getGoalsCount() + footballClub02.getGoalsCount());
                    footballClub.setPoints(footballClub.getPoints() + 3);

                } else if (footballClub.getClubName().equalsIgnoreCase(footballClub01.getClubName())) {
                    footballClub.setScore(footballClub.getScore() + footballClub01.getScore());
                    footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                    footballClub.setDefeatsCount(footballClub.getDefeatsCount() + 1);
                    footballClub.setGoalsCount(footballClub.getGoalsCount() + footballClub01.getGoalsCount());
                    footballClub.setPoints(footballClub.getPoints());
                }

            } else if (footballClub01.getScore() > footballClub02.getScore()) {
                if (footballClub.getClubName().equalsIgnoreCase(footballClub01.getClubName())) {
                    footballClub.setScore(footballClub.getScore() + footballClub01.getScore());
                    footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                    footballClub.setWinCount(footballClub.getWinCount() + 1);
                    footballClub.setGoalsCount(footballClub.getGoalsCount() + footballClub01.getGoalsCount());
                    footballClub.setPoints(footballClub.getPoints() + 3);

                } else if (footballClub.getClubName().equalsIgnoreCase(footballClub02.getClubName())) {
                    footballClub.setScore(footballClub.getScore() + footballClub02.getScore());
                    footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                    footballClub.setDefeatsCount(footballClub.getDefeatsCount() + 1);
                    footballClub.setGoalsCount(footballClub.getGoalsCount() + footballClub02.getGoalsCount());
                    footballClub.setPoints(footballClub.getPoints());
                }

            } else if (footballClub01.getScore() == footballClub02.getScore()) {
                if (footballClub.getClubName().equalsIgnoreCase(footballClub01.getClubName())) {
                    footballClub.setScore(footballClub.getScore() + footballClub01.getScore());
                    footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                    footballClub.setDrawCount(footballClub.getDrawCount() + 1);
                    footballClub.setGoalsCount(footballClub.getGoalsCount() + footballClub02.getGoalsCount());
                    footballClub.setPoints(footballClub.getPoints() + 1);

                } else if (footballClub.getClubName().equalsIgnoreCase(footballClub02.getClubName())) {
                    footballClub.setScore(footballClub.getScore() + footballClub02.getScore());
                    footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                    footballClub.setDrawCount(footballClub.getDrawCount() + 1);
                    footballClub.setGoalsCount(footballClub.getGoalsCount() + footballClub02.getGoalsCount());
                    footballClub.setPoints(footballClub.getPoints() + 1);
                }
            }
        }

        /**
         * save match details in match file
         */

        Match playedMatch = new Match();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String matchDate = simpleDateFormat.format(new Date());

        playedMatch.setMatchDate(matchDate);
        playedMatch.setTeamOneName(footballClub01.getClubName());
        playedMatch.setTeamOneGoal(footballClub01.getGoalsCount());
        playedMatch.setTeamTwoName(footballClub02.getClubName());
        playedMatch.setTeamTwoGoal(footballClub02.getGoalsCount());
        playedMatch.setTeamOneScore(footballClub01.getScore());
        playedMatch.setTeamTwoScore(footballClub02.getScore());

        matchDetails.add(playedMatch);

        saveMatchDataInFile(matchDetails);

        saveClubDetailsInFile(footballClubs);
        return playedMatch;
    }

    @Override
    public void saveMatchDataInFile(List<Match> matchList) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/Users/janithadhananjaya/Self/Projects/Helping/Backend-LeagueManager/Leaguemanager-Spring/src/main/resources/match.json");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(matchList);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveClubDetailsInFile(List<FootballClub> clubList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/Users/janithadhananjaya/Self/Projects/Helping/Backend-LeagueManager/Leaguemanager-Spring/src/main/resources/footballclub.json");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clubList);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Match> findMatchByDate(String date) {
        List<Match> matches = readMatchDetailsFromFile();
        List<Match> results = new ArrayList<>();
        for (Match match : matches) {
            if (match.getMatchDate().equals(date)) {
                results.add(match);
            }
        }
        return results;
    }

    @Override
    public Match generateMatch() {
        //get team's names
        List<FootballClub> footballClubs = readFootBallClubDetailsFromFile();
        List<Match> matches = readMatchDetailsFromFile();

        Random random = new Random();

        //generate no for choosing two teams
        int random01No = random.nextInt(footballClubs.size());
        int team01No = random01No;
        int team02No = team01No;

        while (team01No == team02No) {
            int random02No = random.nextInt(footballClubs.size());
            team02No = random02No;
        }

        // maximum score is 10
        //generate team's goals
        int maxScore = 10;
        int team01Goal = random.nextInt(maxScore + 1);
        int team02Goal = random.nextInt(maxScore + 1);

        int team01Score = team01Goal * 10;
        int team02Score = team02Goal * 10;

        String club01Name = footballClubs.get(team01No).getClubName();
        String club02Name = footballClubs.get(team02No).getClubName();

        FootballClub footballClub01 = new FootballClub();
        footballClub01.setClubName(club01Name);
        footballClub01.setGoalsCount(team01Goal);
        footballClub01.setScore(team01Score);


        FootballClub footballClub02 = new FootballClub();
        footballClub02.setClubName(club02Name);
        footballClub02.setGoalsCount(team02Goal);
        footballClub02.setScore(team02Score);

        return checkMatchScore(footballClub01, footballClub02);

    }


}
