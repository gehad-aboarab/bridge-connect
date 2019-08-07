/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gehad
 */
@Entity
@Table(name = "STAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stages.findAll", query = "SELECT s FROM Stages s")
    , @NamedQuery(name = "Stages.findByStageId", query = "SELECT s FROM Stages s WHERE s.stageId = :stageId")
    , @NamedQuery(name = "Stages.findByStageName", query = "SELECT s FROM Stages s WHERE s.stageName = :stageName")})
public class Stages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STAGE_ID")
    private Integer stageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STAGE_NAME")
    private String stageName;
    @JoinTable(name = "STAGES_DEPENDENCY", joinColumns = {
        @JoinColumn(name = "DEPENDEE_ID", referencedColumnName = "STAGE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "DEPENDENT_ID", referencedColumnName = "STAGE_ID")})
    @ManyToMany
    private Collection<Stages> stagesCollection;
    @ManyToMany(mappedBy = "stagesCollection")
    private Collection<Stages> stagesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stages")
    private Collection<Plans> plansCollection;

    public Stages() {
    }

    public Stages(Integer stageId) {
        this.stageId = stageId;
    }

    public Stages(Integer stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @XmlTransient
    public Collection<Stages> getStagesCollection() {
        return stagesCollection;
    }

    public void setStagesCollection(Collection<Stages> stagesCollection) {
        this.stagesCollection = stagesCollection;
    }

    @XmlTransient
    public Collection<Stages> getStagesCollection1() {
        return stagesCollection1;
    }

    public void setStagesCollection1(Collection<Stages> stagesCollection1) {
        this.stagesCollection1 = stagesCollection1;
    }

    @XmlTransient
    public Collection<Plans> getPlansCollection() {
        return plansCollection;
    }

    public void setPlansCollection(Collection<Plans> plansCollection) {
        this.plansCollection = plansCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stageId != null ? stageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stages)) {
            return false;
        }
        Stages other = (Stages) object;
        if ((this.stageId == null && other.stageId != null) || (this.stageId != null && !this.stageId.equals(other.stageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Stages[ stageId=" + stageId + " ]";
    }
    
}
