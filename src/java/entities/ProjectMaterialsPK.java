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
public class ProjectMaterialsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PROJECT_ID")
    private int projectId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATERIAL_ID")
    private int materialId;

    public ProjectMaterialsPK() {
    }

    public ProjectMaterialsPK(int projectId, int materialId) {
        this.projectId = projectId;
        this.materialId = materialId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) projectId;
        hash += (int) materialId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectMaterialsPK)) {
            return false;
        }
        ProjectMaterialsPK other = (ProjectMaterialsPK) object;
        if (this.projectId != other.projectId) {
            return false;
        }
        if (this.materialId != other.materialId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ProjectMaterialsPK[ projectId=" + projectId + ", materialId=" + materialId + " ]";
    }
    
}
