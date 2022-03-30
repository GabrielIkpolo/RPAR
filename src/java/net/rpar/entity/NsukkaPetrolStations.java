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
@Table(name = "nsukkapetrolstations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsukkaPetrolStations.findAll", query = "SELECT n FROM NsukkaPetrolStations n"),
    @NamedQuery(name = "NsukkaPetrolStations.findByName", query = "SELECT n FROM NsukkaPetrolStations n WHERE n.name = :name"),
    @NamedQuery(name = "NsukkaPetrolStations.findByVolumeofpetrol", query = "SELECT n FROM NsukkaPetrolStations n WHERE n.volumeofpetrol = :volumeofpetrol"),
    @NamedQuery(name = "NsukkaPetrolStations.findByDate", query = "SELECT n FROM NsukkaPetrolStations n WHERE n.date = :date")})
public class NsukkaPetrolStations implements Serializable {

    @Basic(optional = false)
    @Column(name = "capacity")
    private long capacity;

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
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public NsukkaPetrolStations() {
    }

    public NsukkaPetrolStations(String name) {
        this.name = name;
    }

    public NsukkaPetrolStations(String name, String address, double volumeofpetrol, Date date) {
        this.name = name;
        this.address = address;
        this.volumeofpetrol = volumeofpetrol;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof NsukkaPetrolStations)) {
            return false;
        }
        NsukkaPetrolStations other = (NsukkaPetrolStations) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.par.entity.NsukkaPetrolStations[ name=" + name + " ]";
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }
    
}
