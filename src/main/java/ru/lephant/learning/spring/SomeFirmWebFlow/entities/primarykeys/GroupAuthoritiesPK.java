package ru.lephant.learning.spring.SomeFirmWebFlow.entities.primarykeys;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class GroupAuthoritiesPK implements Serializable {
    private long groupId;
    private String authority;

    @Column(name = "group_id", nullable = false)
    @Id
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Column(name = "authority", nullable = false, length = 64)
    @Id
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

        GroupAuthoritiesPK that = (GroupAuthoritiesPK) o;

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
}
