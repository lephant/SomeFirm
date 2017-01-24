package ru.lephant.learning.spring.SomeFirmWebFlow.team.service;

import org.primefaces.model.DualListModel;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Team;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.TeamSearchCriteria;

import java.util.List;

public interface TeamService {

    public List listTeam(TeamSearchCriteria searchCriteria);

    public Team getTeamById(long id);

    public void saveTeam(Team team);

    public DualListModel getFreeWorkers(long from, long to);

}
