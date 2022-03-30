/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.rpar.entity.NsukkaPetrolStations;
import net.rpar.entity.PetrolInfo;
import net.rpar.jpaejb.NsukkaPetrolStationsFacade;
import net.rpar.jpaejb.PetrolInfoFacade;
import net.rpar.model.bean.NsukkaPetrolStationsBean;
import net.rpar.model.bean.PetrolInfoBean;

/**
 *
 * @author Aaron
 */

@Named (value="petroInfoController")
@ApplicationScoped
public class PetrolInfoController implements Serializable {
    
    @EJB
    PetrolInfoFacade petrolInfoFacade;
    
    @Inject
    PetrolInfoBean petrolInfoBean;
    
    @Inject
    NsukkaPetrolStationsBean nsukkaPetrolStationsBean;
    
    @Inject
    NsukkaPetrolStationsFacade nsukkaPetrolStationsFacade;
    
   
    public List<PetrolInfo> listAllPetrolStations() {
        return petrolInfoFacade.findAll();
    }
  
        // For everydata in  NsukkaPetrolStations insert it into PetrolInfo
    public void copyNsukkaPetrolStationsToPetrolInfo(){
          List<NsukkaPetrolStations> presentNsukkaPetrolStations = nsukkaPetrolStationsFacade.findAll();
           List<PetrolInfo> presentPetrolInfo = petrolInfoFacade.findAll();
           for (PetrolInfo pInfo: presentPetrolInfo){
               PetrolInfo firstPetrolInfo = new PetrolInfo();
               firstPetrolInfo.setName(pInfo.getName());
               firstPetrolInfo.setAddress(pInfo.getAddress());
               firstPetrolInfo.setVolumeofpetrol(pInfo.getVolumeofpetrol());
               firstPetrolInfo.setDatetime(pInfo.getDatetime());
               petrolInfoFacade.remove(firstPetrolInfo);
           }
          System.out.println("---->    Entities have been removed");
          
          for(NsukkaPetrolStations np : presentNsukkaPetrolStations){
              PetrolInfo thePetrolInfo = new PetrolInfo();
              thePetrolInfo.setName(np.getName());
              thePetrolInfo.setAddress(np.getAddress());
              thePetrolInfo.setVolumeofpetrol(np.getVolumeofpetrol());
              thePetrolInfo.setDatetime(np.getDate());
                    if(thePetrolInfo.toString().equals(petrolInfoFacade.findAll().toString())){
                        break; // the if statement have not been verified
                    }
              petrolInfoFacade.create(thePetrolInfo);
          }
          System.out.println("Message from Aaron  ==>  Finished populating PetrolInfo table");
    }
    
    
    //=====================================================
            //Getters and Setters
    //=====================================================
    
    
    public PetrolInfoFacade getPetrolInfoFacade() {
        return petrolInfoFacade;
    }

    public void setPetrolInfoFacade(PetrolInfoFacade petrolInfoFacade) {
        this.petrolInfoFacade = petrolInfoFacade;
    }

    public PetrolInfoBean getPetrolInfoBean() {
        return petrolInfoBean;
    }

    public void setPetrolInfoBean(PetrolInfoBean petrolInfoBean) {
        this.petrolInfoBean = petrolInfoBean;
    }
    
    
    //======================================================
            //More Getters and Setters 
    //---------------------------------------
     
    public NsukkaPetrolStationsBean getNsukkaPetrolStationsBean() {
        return nsukkaPetrolStationsBean;
    }

    public void setNsukkaPetrolStationsBean(NsukkaPetrolStationsBean nsukkaPetrolStationsBean) {
        this.nsukkaPetrolStationsBean = nsukkaPetrolStationsBean;
    }

    public NsukkaPetrolStationsFacade getNsukkaPetrolStationsFacade() {
        return nsukkaPetrolStationsFacade;
    }

    public void setNsukkaPetrolStationsFacade(NsukkaPetrolStationsFacade nsukkaPetrolStationsFacade) {
        this.nsukkaPetrolStationsFacade = nsukkaPetrolStationsFacade;
    }
   
}
