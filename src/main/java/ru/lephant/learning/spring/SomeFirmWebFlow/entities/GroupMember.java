package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_members", schema = "somefirmdb")
public class GroupMember implements Serializable {
    private long id;
    private User user;
    private Group group;


    public GroupMember() {
    }

    public GroupMember(User user, Group group) {
        this.user = user;
        this.group = group;
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

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group groupId) {
        this.group = groupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMember that = (GroupMember) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return group != null ? group.equals(that.group) : that.group == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
