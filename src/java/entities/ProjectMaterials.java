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
@Table(name = "PROJECT_MATERIALS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectMaterials.findAll", query = "SELECT p FROM ProjectMaterials p")
    , @NamedQuery(name = "ProjectMaterials.findByProjectId", query = "SELECT p FROM ProjectMaterials p WHERE p.projectMaterialsPK.projectId = :projectId")
    , @NamedQuery(name = "ProjectMaterials.findByMaterialId", query = "SELECT p FROM ProjectMaterials p WHERE p.projectMaterialsPK.materialId = :materialId")
    , @NamedQuery(name = "ProjectMaterials.findByQuantity", query = "SELECT p FROM ProjectMaterials p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "ProjectMaterials.findByTotalCost", query = "SELECT p FROM ProjectMaterials p WHERE p.totalCost = :totalCost")})
public class ProjectMaterials implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProjectMaterialsPK projectMaterialsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_COST")
    private double totalCost;
    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materials materials;
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projects projects;

    public ProjectMaterials() {
    }

    public ProjectMaterials(ProjectMaterialsPK projectMaterialsPK) {
        this.projectMaterialsPK = projectMaterialsPK;
    }

    public ProjectMaterials(ProjectMaterialsPK projectMaterialsPK, int quantity, double totalCost) {
        this.projectMaterialsPK = projectMaterialsPK;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public ProjectMaterials(int projectId, int materialId) {
        this.projectMaterialsPK = new ProjectMaterialsPK(projectId, materialId);
    }

    public ProjectMaterialsPK getProjectMaterialsPK() {
        return projectMaterialsPK;
    }

    public void setProjectMaterialsPK(ProjectMaterialsPK projectMaterialsPK) {
        this.projectMaterialsPK = projectMaterialsPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Materials getMaterials() {
        return materials;
    }

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectMaterialsPK != null ? projectMaterialsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectMaterials)) {
            return false;
        }
        ProjectMaterials other = (ProjectMaterials) object;
        if ((this.projectMaterialsPK == null && other.projectMaterialsPK != null) || (this.projectMaterialsPK != null && !this.projectMaterialsPK.equals(other.projectMaterialsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ProjectMaterials[ projectMaterialsPK=" + projectMaterialsPK + " ]";
    }
    
}
