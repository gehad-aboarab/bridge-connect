/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gehad
 */
@Embeddable
public class PlansPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "STAGE_ID")
    private int stageId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROJECT_ID")
    private int projectId;

    public PlansPK() {
    }

    public PlansPK(int stageId, int projectId) {
        this.stageId = stageId;
        this.projectId = projectId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) stageId;
        hash += (int) projectId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlansPK)) {
            return false;
        }
        PlansPK other = (PlansPK) object;
        if (this.stageId != other.stageId) {
            return false;
        }
        if (this.projectId != other.projectId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.PlansPK[ stageId=" + stageId + ", projectId=" + projectId + " ]";
    }
    
}
