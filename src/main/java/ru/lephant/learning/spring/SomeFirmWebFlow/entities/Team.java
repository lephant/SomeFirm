package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Team implements Serializable {
    private long id;
    private ProductType productType;
    private int count;
    private long dateOfCreate;
    private long dateOfDeadline;
    private Set<TeamUser> workers;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pressmark", referencedColumnName = "pressmark")
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<TeamUser> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<TeamUser> workers) {
        this.workers = workers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (count != team.count) return false;
        if (dateOfCreate != team.dateOfCreate) return false;
        if (dateOfDeadline != team.dateOfDeadline) return false;
        if (!productType.equals(team.productType)) return false;
        return workers.equals(team.workers);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + productType.hashCode();
        result = 31 * result + count;
        result = 31 * result + (int) (dateOfCreate ^ (dateOfCreate >>> 32));
        result = 31 * result + (int) (dateOfDeadline ^ (dateOfDeadline >>> 32));
        result = 31 * result + workers.hashCode();
        return result;
    }
}
