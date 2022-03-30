/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author angelis
 */
@Entity
@Table(name = "petrolinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PetrolInfo.findAll", query = "SELECT p FROM PetrolInfo p"),
    @NamedQuery(name = "PetrolInfo.findByName", query = "SELECT p FROM PetrolInfo p WHERE p.name = :name"),
    @NamedQuery(name = "PetrolInfo.findByVolumeofpetrol", query = "SELECT p FROM PetrolInfo p WHERE p.volumeofpetrol = :volumeofpetrol"),
    @NamedQuery(name = "PetrolInfo.findByDatetime", query = "SELECT p FROM PetrolInfo p WHERE p.datetime = :datetime")})
public class PetrolInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "volumeofpetrol")
    private double volumeofpetrol;
    @Basic(optional = false)
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    public PetrolInfo() {
    }

    public PetrolInfo(String name) {
        this.name = name;
    }

    public PetrolInfo(String name, String address, double volumeofpetrol, Date datetime) {
        this.name = name;
        this.address = address;
        this.volumeofpetrol = volumeofpetrol;
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getVolumeofpetrol() {
        return volumeofpetrol;
    }

    public void setVolumeofpetrol(double volumeofpetrol) {
        this.volumeofpetrol = volumeofpetrol;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetrolInfo)) {
            return false;
        }
        PetrolInfo other = (PetrolInfo) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.par.entity.PetrolInfo[ name=" + name + " ]";
    }
    
}
