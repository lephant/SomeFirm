package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.primarykeys.GroupAuthoritiesPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_authorities", schema = "somefirmdb")
@IdClass(GroupAuthoritiesPK.class)
public class GroupAuthorities implements Serializable {
    private long groupId;
    private String authority;

    @Id
    @Column(name = "group_id", nullable = false)
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "authority", nullable = false, length = 64)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupAuthorities that = (GroupAuthorities) o;

        if (groupId != that.groupId) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (groupId ^ (groupId >>> 32));
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupAuthorities{" +
                "groupId=" + groupId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
