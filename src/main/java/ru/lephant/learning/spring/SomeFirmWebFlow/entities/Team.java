package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Team implements Serializable {
    private long id;
    private OrderThing order;
    private long dateOfCreate;
    private long dateOfDeadline;
    private List<TeamUser> workers = new ArrayList<>();


    public Team() {
    }

    public Team(OrderThing order) {
        this.order = order;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id", unique = true)
    public OrderThing getOrder() {
        return order;
    }

    public void setOrder(OrderThing order) {
        this.order = order;
    }

    @Basic
    @Column(name = "date_of_create", nullable = false)
    public long getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(long dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Basic
    @Column(name = "date_of_deadline", nullable = false)
    public long getDateOfDeadline() {
        return dateOfDeadline;
    }

    public void setDateOfDeadline(long dateOfDeadline) {
        this.dateOfDeadline = dateOfDeadline;
    }

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<TeamUser> getWorkers() {
        return workers;
    }

    public void setWorkers(List<TeamUser> workers) {
        this.workers = workers;
    }

    public void addWorkers(ArrayList<User> users) {
        for (User user: users) {
            workers.add(new TeamUser(this, user));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (dateOfCreate != team.dateOfCreate) return false;
        if (dateOfDeadline != team.dateOfDeadline) return false;
        if (!order.equals(team.order)) return false;
        return workers != null ? workers.equals(team.workers) : team.workers == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + order.hashCode();
        result = 31 * result + (int) (dateOfCreate ^ (dateOfCreate >>> 32));
        result = 31 * result + (int) (dateOfDeadline ^ (dateOfDeadline >>> 32));
        result = 31 * result + (workers != null ? workers.hashCode() : 0);
        return result;
    }
}
