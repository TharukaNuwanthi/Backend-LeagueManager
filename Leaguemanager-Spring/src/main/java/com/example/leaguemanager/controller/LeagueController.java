package com.example.leaguemanager.controller;

import com.example.leaguemanager.model.FootballClub;
import com.example.leaguemanager.model.Match;
import com.example.leaguemanager.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/league")
@CrossOrigin
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/get-league-points-table")
    public List<FootballClub> getLeaguePointsTable(){
        List<FootballClub> leaguePointsTable = leagueService.getLeaguePointsTable();
        return leaguePointsTable;
    }

    @PostMapping("/get-match-by-date")
    public List<Match> getMatchByDate(@RequestBody Map<String, Object> params){
        List<Match> matchByDate = leagueService.findMatchByDate(params.get("date").toString());
        return matchByDate;
    }

    @GetMapping("/generate-match")
    public Match generateMatch(){
        return leagueService.generateMatch();
    }
}
