package com.example.competitionteamservice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CompetitionTeam.class)
@Table(name = "competition_team")
public class CompetitionTeam implements Serializable {
	
	@Id
	private Long teamId;
	
	@Id
	private Long competitionId;
	
	public CompetitionTeam() {
		
	}
	
	public CompetitionTeam(Long teamId, Long competitionId) {
        this.teamId = teamId;
        this.competitionId = competitionId;
    }

	public Long getTeamId() {
		return teamId;
	}

	public Long getCompetitionId() {
		return competitionId;
	}
	
}