/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Aaron
 */
@Named (value="nsukkaPetrolStationBean")
@SessionScoped
public class NsukkaPetrolStationsBean implements Serializable {
    
    private String name;
    private String address;
    private Double volumeOfPetrol;
    private Long capacity;
    private Date date;

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

    public Double getVolumeOfPetrol() {
        return volumeOfPetrol;
    }

    public void setVolumeOfPetrol(Double volumeOfPetrol) {
        this.volumeOfPetrol = volumeOfPetrol;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   @Override
    public String toString() {
        return "NsukkaPetrolStationsBean{" + "name=" + name + ", address=" + address + ", volumeOfPetrol=" + volumeOfPetrol + ", capacity=" + capacity + ", date=" + date + '}';
    } 
  
}
