package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Operation implements Serializable {
    private long id;
    private String description;
    private long duration;
    private Plan plan;
    private Workshop defaultWorkshop;
    private List<OperationSacrificialMaterial> sacrificialMaterials;
    private Set<OperationTool> tools;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "duration", nullable = false)
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan planId) {
        this.plan = planId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "default_workshop", referencedColumnName = "id")
    public Workshop getDefaultWorkshop() {
        return defaultWorkshop;
    }

    public void setDefaultWorkshop(Workshop defaultWorkshop) {
        this.defaultWorkshop = defaultWorkshop;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operation", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<OperationSacrificialMaterial> getSacrificialMaterials() {
        return sacrificialMaterials;
    }

    public void setSacrificialMaterials(List<OperationSacrificialMaterial> sacrificialMaterials) {
        this.sacrificialMaterials = sacrificialMaterials;
    }

    public void addSacrificialMaterial(OperationSacrificialMaterial operationSacrificialMaterial) {
        sacrificialMaterials.add(operationSacrificialMaterial);
    }

    public void removeSacrificialMaterial(OperationSacrificialMaterial operationSacrificialMaterial) {
        if(sacrificialMaterials.contains(operationSacrificialMaterial))
            sacrificialMaterials.remove(operationSacrificialMaterial);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operation")
    public Set<OperationTool> getTools() {
        return tools;
    }

    public void setTools(Set<OperationTool> tools) {
        this.tools = tools;
    }

    /*public void addTool(OperationTool operationTool) {
        tools.add(operationTool);
    }

    public void removeTool(OperationTool operationTool) {
        if(tools.contains(operationTool))
            tools.remove(operationTool);
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != operation.id) return false;
        if (duration != operation.duration) return false;
        if (description != null ? !description.equals(operation.description) : operation.description != null)
            return false;
        if (!plan.equals(operation.plan)) return false;
        if (defaultWorkshop != null ? !defaultWorkshop.equals(operation.defaultWorkshop) : operation.defaultWorkshop != null)
            return false;
        if (sacrificialMaterials != null ? !sacrificialMaterials.equals(operation.sacrificialMaterials) : operation.sacrificialMaterials != null)
            return false;
        return tools != null ? tools.equals(operation.tools) : operation.tools == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + plan.hashCode();
        result = 31 * result + (defaultWorkshop != null ? defaultWorkshop.hashCode() : 0);
        result = 31 * result + (sacrificialMaterials != null ? sacrificialMaterials.hashCode() : 0);
        result = 31 * result + (tools != null ? tools.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return description;
    }
}
