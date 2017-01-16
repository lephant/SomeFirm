package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "team_users", schema = "somefirmdb")
public class TeamUser implements Serializable {

    private long id;

    @NotNull(message = "Не указан наряд, в который добавляется рабочий!")
    private Team team;

    @NotNull(message = "Не указан рабочий, который вступает в наряд!")
    private User user;


    public TeamUser() {
    }

    public TeamUser(Team team, User user) {
        this.team = team;
        this.user = user;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team teamId) {
        this.team = teamId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamUser teamUser = (TeamUser) o;

        if (id != teamUser.id) return false;
        if (!team.equals(teamUser.team)) return false;
        return user.equals(teamUser.user);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        return result;
    }
}
