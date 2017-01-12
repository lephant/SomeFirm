package ru.lephant.learning.spring.SomeFirmWebFlow.team.service.impl;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Team;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.TeamUser;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.TeamSearchCriteria;
import ru.lephant.learning.spring.SomeFirmWebFlow.team.dao.TeamDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.team.service.TeamService;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.UserDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDAO teamDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public List listTeam(TeamSearchCriteria searchCriteria) {
        return teamDAO.listTeam(searchCriteria);
    }

    @Override
    @Transactional(readOnly = true)
    public Team getTeamById(long id) {
        return teamDAO.getTeamById(id);
    }

    @Override
    @Transactional
    public void saveTeam(Team team) {
        teamDAO.saveTeam(team);
    }

    @Override
    @Transactional(readOnly = true)
    public DualListModel getFreeWorkers(long from, long to) {
        List<Team> actualTeams = teamDAO.getActualTeamsWithWorkersByTime(from, to);

        Set<User> engagedWorkers = new HashSet<User>();
        for (Team team: actualTeams) {
            for (TeamUser user: team.getWorkers()) {
                engagedWorkers.add(user.getUser());
            }
        }

        List<User> anotherWorkers = userDAO.getAnotherWorkers(engagedWorkers);

        DualListModel<User> freeWorkers = new DualListModel<>();
        freeWorkers.setSource(anotherWorkers);

        return freeWorkers;
    }
}
