package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import ru.lephant.learning.spring.SomeFirmWebFlow.enums.GroupType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "somefirmdb")
public class User implements Serializable {

    @Size(min = 6, max = 255, message = "Логин должен быть от 6 до 255 символов!")
    private String username;

    @Size(min = 6, max = 128, message = "Пароль должен быть от 6 до 128 символов!")
    private String password;

    private byte enabled;

    private GroupMember groupMember;

    private List<OrderThing> orders = new ArrayList<>();


    public User() {
        this.enabled = 1;
        this.groupMember = new GroupMember(this, GroupType.USER_GROUP.getGroup());
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = 1;
        this.groupMember = new GroupMember(this, GroupType.USER_GROUP.getGroup());
    }

    public User(String username, String password, GroupMember groupMember) {
        this.username = username;
        this.password = password;
        this.enabled = 1;
        this.groupMember = groupMember;
    }


    @Id
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public GroupMember getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMember groupMember) {
        this.groupMember = groupMember;
    }

    @OneToMany(mappedBy = "user")
    public List<OrderThing> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderThing> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User users = (User) o;

        if (enabled != users.enabled) return false;
        if (username != null ? !username.equals(users.username) : users.username != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) enabled;
        return result;
    }

    @Override
    public String toString() {
        return username;
    }
}
