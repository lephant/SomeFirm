package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import ru.lephant.learning.spring.SomeFirmWebFlow.enums.OrderState;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_thing", schema = "somefirmdb")
public class OrderThing implements Serializable {
    private long id;
    private User user;
    private Thing thing;
    private int count;
    private String description;
    private OrderState state;
    private Team team;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    public User getUser() {
        return user;
    }

    public void setUser(User username) {
        this.user = username;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pressmark", referencedColumnName = "pressmark")
    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing pressmark) {
        this.thing = pressmark;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 4096)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @OneToOne(mappedBy = "order")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderThing that = (OrderThing) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (!user.equals(that.user)) return false;
        if (!thing.equals(that.thing)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return state == that.state;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + thing.hashCode();
        result = 31 * result + count;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + state.hashCode();
        return result;
    }
}
