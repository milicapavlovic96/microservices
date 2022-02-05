package com.example.competitionteamservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamCompetitionRepository extends JpaRepository<CompetitionTeam, Long> {
	List<CompetitionTeam> findByTeamId(Long teamId);
	List<CompetitionTeam> findByCompetitionId(Long competitionId);
	Optional<CompetitionTeam> findByTeamIdAndCompetitionId(Long teamId, Long competitionId);
}
