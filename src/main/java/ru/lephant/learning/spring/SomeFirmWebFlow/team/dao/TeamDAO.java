package ru.lephant.learning.spring.SomeFirmWebFlow.team.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Team;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.TeamSearchCriteria;

import java.util.List;

public interface TeamDAO {

    public List listTeam(TeamSearchCriteria searchCriteria);

    public Team getTeamById(long id);

    public void saveTeam(Team team);

    public List<Team> getActualTeamsWithWorkersByTime(long from, long to);
}
