package com.example.competitionteamservice;

public class CompetitionDTO {
	private String competitionName;

	public  CompetitionDTO() {

	}

	public  CompetitionDTO(String competitionName) {
		super();
		this.competitionName = competitionName;
	}

	public String getCourseName() {
		return competitionName;
	}
}
