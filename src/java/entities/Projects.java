/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gehad
 */
@Entity
@Table(name = "PROJECTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p")
    , @NamedQuery(name = "Projects.findByProjectId", query = "SELECT p FROM Projects p WHERE p.projectId = :projectId")
    , @NamedQuery(name = "Projects.findByProjectName", query = "SELECT p FROM Projects p WHERE p.projectName = :projectName")
    , @NamedQuery(name = "Projects.findByLocation", query = "SELECT p FROM Projects p WHERE p.location = :location")
    , @NamedQuery(name = "Projects.findByStartDate", query = "SELECT p FROM Projects p WHERE p.startDate = :startDate")
    , @NamedQuery(name = "Projects.findByFinishDate", query = "SELECT p FROM Projects p WHERE p.finishDate = :finishDate")})
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROJECT_ID")
    private Integer projectId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LOCATION")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FINISH_DATE")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @ManyToMany(mappedBy = "projectsCollection")
    private Collection<Managers> managersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projects")
    private Collection<Plans> plansCollection;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(optional = false)
    private Clients clientId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projects")
    private Collection<ProjectMaterials> projectMaterialsCollection;

    public Projects() {
    }

    public Projects(Integer projectId) {
        this.projectId = projectId;
    }

    public Projects(Integer projectId, String projectName, String location, Date startDate, Date finishDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.location = location;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @XmlTransient
    public Collection<Managers> getManagersCollection() {
        return managersCollection;
    }

    public void setManagersCollection(Collection<Managers> managersCollection) {
        this.managersCollection = managersCollection;
    }

    @XmlTransient
    public Collection<Plans> getPlansCollection() {
        return plansCollection;
    }

    public void setPlansCollection(Collection<Plans> plansCollection) {
        this.plansCollection = plansCollection;
    }

    public Clients getClientId() {
        return clientId;
    }

    public void setClientId(Clients clientId) {
        this.clientId = clientId;
    }

    @XmlTransient
    public Collection<ProjectMaterials> getProjectMaterialsCollection() {
        return projectMaterialsCollection;
    }

    public void setProjectMaterialsCollection(Collection<ProjectMaterials> projectMaterialsCollection) {
        this.projectMaterialsCollection = projectMaterialsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Projects[ projectId=" + projectId + " ]";
    }
    
}
