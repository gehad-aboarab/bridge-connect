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
import javax.persistence.ManyToOne;
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
@Table(name = "MATERIALS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materials.findAll", query = "SELECT m FROM Materials m")
    , @NamedQuery(name = "Materials.findByMaterialId", query = "SELECT m FROM Materials m WHERE m.materialId = :materialId")
    , @NamedQuery(name = "Materials.findByMaterialName", query = "SELECT m FROM Materials m WHERE m.materialName = :materialName")
    , @NamedQuery(name = "Materials.findByPrice", query = "SELECT m FROM Materials m WHERE m.price = :price")})
public class Materials implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MATERIAL_ID")
    private Integer materialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MATERIAL_NAME")
    private String materialName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materials")
    private Collection<ProjectMaterials> projectMaterialsCollection;
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    @ManyToOne(optional = false)
    private Suppliers supplierId;

    public Materials() {
    }

    public Materials(Integer materialId) {
        this.materialId = materialId;
    }

    public Materials(Integer materialId, String materialName, double price) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.price = price;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<ProjectMaterials> getProjectMaterialsCollection() {
        return projectMaterialsCollection;
    }

    public void setProjectMaterialsCollection(Collection<ProjectMaterials> projectMaterialsCollection) {
        this.projectMaterialsCollection = projectMaterialsCollection;
    }

    public Suppliers getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Suppliers supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materials)) {
            return false;
        }
        Materials other = (Materials) object;
        if ((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Materials[ materialId=" + materialId + " ]";
    }
    
}
