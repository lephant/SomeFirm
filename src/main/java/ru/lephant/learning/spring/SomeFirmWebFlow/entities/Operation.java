package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Operation implements Serializable {
    private long id;
    private String description;
    private long duration;
    private byte[] plan;
    private Workshop defaultWorkshop;
    private List<OperationSacrificialMaterial> sacrificialMaterials = new ArrayList<OperationSacrificialMaterial>();
    private List<OperationTool> tools = new ArrayList<OperationTool>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "plan", nullable = true)
    public byte[] getPlan() {
        return plan;
    }

    public void setPlan(byte[] plan) {
        this.plan = plan;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operation", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<OperationTool> getTools() {
        return tools;
    }

    public void setTools(List<OperationTool> tools) {
        this.tools = tools;
    }

    public void addTool(OperationTool operationTool) {
        tools.add(operationTool);
    }

    public void removeTool(OperationTool operationTool) {
        if(tools.contains(operationTool))
            tools.remove(operationTool);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != operation.id) return false;
        if (duration != operation.duration) return false;
        if (description != null ? !description.equals(operation.description) : operation.description != null)
            return false;
        if (!Arrays.equals(plan, operation.plan)) return false;
        return defaultWorkshop != null ? defaultWorkshop.equals(operation.defaultWorkshop) : operation.defaultWorkshop == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + Arrays.hashCode(plan);
        result = 31 * result + (defaultWorkshop != null ? defaultWorkshop.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
