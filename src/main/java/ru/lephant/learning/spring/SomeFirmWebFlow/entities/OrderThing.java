package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_thing", schema = "somefirmdb")
public class OrderThing {
    private long id;
    private User user;
    private Pressmarks pressmark;
    private int count;
    private String description;

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
    public Pressmarks getPressmark() {
        return pressmark;
    }

    public void setPressmark(Pressmarks pressmark) {
        this.pressmark = pressmark;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderThing that = (OrderThing) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (!user.equals(that.user)) return false;
        if (!pressmark.equals(that.pressmark)) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + pressmark.hashCode();
        result = 31 * result + count;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
