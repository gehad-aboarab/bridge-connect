/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gehad
 */
@Entity
@Table(name = "PLANS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plans.findAll", query = "SELECT p FROM Plans p")
    , @NamedQuery(name = "Plans.findByStageId", query = "SELECT p FROM Plans p WHERE p.plansPK.stageId = :stageId")
    , @NamedQuery(name = "Plans.findByProjectId", query = "SELECT p FROM Plans p WHERE p.plansPK.projectId = :projectId")
    , @NamedQuery(name = "Plans.findByCompleted", query = "SELECT p FROM Plans p WHERE p.completed = :completed")})
public class Plans implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlansPK plansPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPLETED")
    private int completed;
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projects projects;
    @JoinColumn(name = "STAGE_ID", referencedColumnName = "STAGE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stages stages;

    public Plans() {
    }

    public Plans(PlansPK plansPK) {
        this.plansPK = plansPK;
    }

    public Plans(PlansPK plansPK, int completed) {
        this.plansPK = plansPK;
        this.completed = completed;
    }

    public Plans(int stageId, int projectId) {
        this.plansPK = new PlansPK(stageId, projectId);
    }

    public PlansPK getPlansPK() {
        return plansPK;
    }

    public void setPlansPK(PlansPK plansPK) {
        this.plansPK = plansPK;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Stages getStages() {
        return stages;
    }

    public void setStages(Stages stages) {
        this.stages = stages;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plansPK != null ? plansPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plans)) {
            return false;
        }
        Plans other = (Plans) object;
        if ((this.plansPK == null && other.plansPK != null) || (this.plansPK != null && !this.plansPK.equals(other.plansPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Plans[ plansPK=" + plansPK + " ]";
    }
    
}
