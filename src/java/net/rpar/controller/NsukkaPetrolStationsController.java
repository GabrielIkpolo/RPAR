/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.rpar.entity.NsukkaPetrolStations;
import net.rpar.jpaejb.NsukkaPetrolStationsFacade;
import net.rpar.model.bean.NsukkaPetrolStationsBean;

/**
 *
 * @author Aaron
 */


@Stateless
@Named (value="nsukkaPetrolStationsController")
@Dependent
public class NsukkaPetrolStationsController implements Serializable {
    
    private Double fullPetrol = 6000.0;
    private Double presentVolumeOfPetrol;
    private Double theSubtractedLiterByThirty;
    private List<NsukkaPetrolStations>listOfNsukkaPetrolStations;
    private double theDoubleValueOfCapacity;

    @Inject
    NsukkaPetrolStationsBean nsukkaPetrolStationsBean;
    
    NsukkaPetrolStations nsukkaPetrolStationEntity;

    @EJB
    NsukkaPetrolStationsFacade nsukkaPetrolStationsFacade;
    
    @Schedule(second="*/35", minute="*", hour = "*")
    public void beginSchedulling(){
        updateNsukkaPetrolStaionWithRandomisedValues();
    }
    

    @PostConstruct
    public void init() {
        try {
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * To randomize the petrol values
     */
    public double randomizeThePetrolValue( double dVolume){
        Random random = new Random();
        double theRandomizedValue = (random.nextDouble() * dVolume) + 1 ;
        DecimalFormat df = new DecimalFormat("#####.0");
        String stringValueOfCapacity = df.format(theRandomizedValue);
        theDoubleValueOfCapacity = Double.parseDouble(stringValueOfCapacity);
        return  theDoubleValueOfCapacity;
    }
    
    
      // Will do an update statement for every Nsukker petrol Satiotion. Updates the volume of petrol
    public void automaticallyUpdateAllNsukkaPetrolStations() {
            listOfNsukkaPetrolStations = nsukkaPetrolStationsFacade.findAll();
            for (NsukkaPetrolStations e : listOfNsukkaPetrolStations) {
                e.setVolumeofpetrol(presentVolumeOfPetrol);
                nsukkaPetrolStationsFacade.edit(e);
            }
    }
    
    //Randomises values
    public Double toRandomise(double dVolume){
        Random random = new Random();
        int theRandomizedValue = random.nextInt((int) dVolume + 2000) + 1;
        DecimalFormat df = new DecimalFormat("#####.0");
        String stringValueOfTheVolumeOfPetrol = df.format(theRandomizedValue);
        double theDoubleValueOfPetrol = Double.parseDouble(stringValueOfTheVolumeOfPetrol);
        return theDoubleValueOfPetrol;
    }
    
    
    /** Update the volumes of every NsukkaPetrol Station with a random value */
    public void updateNsukkaPetrolStaionWithRandomisedValues(){
        listOfNsukkaPetrolStations = nsukkaPetrolStationsFacade.findAll();
        for(NsukkaPetrolStations e: listOfNsukkaPetrolStations){
            e.setVolumeofpetrol(toRandomise(e.getVolumeofpetrol()));
            nsukkaPetrolStationsFacade.edit(e);
        }
    }
    
 
       /** Update every NsukkaPetrolStation to 6,000 */
     public void updateEveryNsukkaPetrolStationToSixThousandLiters() {
         listOfNsukkaPetrolStations = nsukkaPetrolStationsFacade.findAll();
         listOfNsukkaPetrolStations.stream().map((NsukkaPetrolStations e) -> {
             e.setVolumeofpetrol(6000.0);
             return e;
         }).forEachOrdered((e) -> {
             nsukkaPetrolStationsFacade.edit(e);
         });
    }
     
    /** Subtracts thirty liters every five Minutes*/
    public Double minusThirtyLitre(){
        presentVolumeOfPetrol = fullPetrol - 30;
        fullPetrol = (fullPetrol -30);
        return presentVolumeOfPetrol;
    }
    
      /** Checks if petrol is less than 30 liters; if it is, wait for 8 hrs and fill to the brim (6,000Liters) */
    public Double checkIfPetrolIsEmptyAndDoSomethingIfItIs(){
            if(presentVolumeOfPetrol <= 0 ){
                try {
                    TimeUnit.MINUTES.sleep(10);
                    updateEveryNsukkaPetrolStationToSixThousandLiters();
                    fullPetrol = 6000.0;
                } catch (InterruptedException ex) {
                    Logger.getLogger(NsukkaPetrolStationsController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        return presentVolumeOfPetrol;
    }
    
    /** Finds NsukkaPetrolStations by name*/
    public NsukkaPetrolStations findNsukkaPerolStations(NsukkaPetrolStations name){
        return nsukkaPetrolStationsFacade.find(name);
    }
    
    //Add Petrol Stations
    public void addPetrolStation() {
        try {
            NsukkaPetrolStations nP = new NsukkaPetrolStations();
            nP.setName(nsukkaPetrolStationsBean.getName());
            nP.setAddress(nsukkaPetrolStationsBean.getAddress());
            nP.setVolumeofpetrol(nsukkaPetrolStationsBean.getVolumeOfPetrol());
            nP.setCapacity(nsukkaPetrolStationsBean.getCapacity());
            nP.setDate(new Date());

            nsukkaPetrolStationsFacade.create(nP);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A new Record of Petrol Station has been added"));
            clearFiledData();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry, record was NOT added"));
            ex.printStackTrace();
        }
    }

    public void clearFiledData(){
        nsukkaPetrolStationsBean.setName("");
        nsukkaPetrolStationsBean.setAddress("");
        nsukkaPetrolStationsBean.setVolumeOfPetrol(null);
        nsukkaPetrolStationsBean.setCapacity(null);
    }
    
    /**
     * Get the Number of Petrol Stations
     */
    public int countNumbersOfNsukkaPetrolStations() {
        return nsukkaPetrolStationsFacade.count();
    }   
    
 /** Find All Nsukka Petrol Stations*/
    public List<NsukkaPetrolStations> getAllNsukkaPetrolStations(){
        return nsukkaPetrolStationsFacade.findAll();
    }
    
    /** To delete a petrol station*/
    public void deletePetrolStations(NsukkaPetrolStations entity){
        nsukkaPetrolStationsFacade.remove(entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Petrol Station Deleted"));
    }
    
    /** To edit petrol station */
    public void edit(NsukkaPetrolStations nP){
        nsukkaPetrolStationsBean.setName(nP.getName());
        nsukkaPetrolStationsBean.setAddress(nP.getAddress());
        nsukkaPetrolStationsBean.setCapacity(nP.getCapacity());
        nsukkaPetrolStationsBean.setVolumeOfPetrol(nP.getVolumeofpetrol());
        nsukkaPetrolStationsBean.setDate(nP.getDate());
        
       // return "editPage.jsf?faces-redirect=true";
    }
    
    /** To save */
    public void updateRecords(){
        NsukkaPetrolStations nP = new NsukkaPetrolStations(nsukkaPetrolStationsBean.getName());
        nP.setAddress(nsukkaPetrolStationsBean.getAddress());
        nP.setCapacity(nsukkaPetrolStationsBean.getCapacity());
        nP.setVolumeofpetrol(nsukkaPetrolStationsBean.getVolumeOfPetrol());
        nP.setDate(nsukkaPetrolStationsBean.getDate());
        
        nsukkaPetrolStationsFacade.edit(nP);
        clearFiledData();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Record Saved"));
        //return "admin.jsf?faces-redirect=true";
    }
    
    /** updates the record of the petrol station */
    public void updatePetrolStation(NsukkaPetrolStations entity){
        entity.setName(nsukkaPetrolStationsBean.getName());
        entity.setAddress(nsukkaPetrolStationsBean.getAddress());
        entity.setCapacity(nsukkaPetrolStationsBean.getCapacity());
        entity.setVolumeofpetrol(nsukkaPetrolStationsBean.getVolumeOfPetrol());
        //entity.setDate(nsukkaPetrolStationsBean.getDate());
        
        nsukkaPetrolStationsFacade.edit(entity);
    }
    
    
    public NsukkaPetrolStations findById( String name){
        return nsukkaPetrolStationsFacade.find(name);
    }
    
    public void lickRefresher(){
        
    }
    
//===============================================================================
        // Getters and setters here
//---------------------------------------------------------------------
    
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
    
    public Double getFullPetrol() {
        return fullPetrol;
    }

    public void setFullPetrol(Double fullPetrol) {
        this.fullPetrol = fullPetrol;
    }

    public Double getPresentVolumeOfPetrol() {
        return presentVolumeOfPetrol;
    }

    public void setPresentVolumeOfPetrol(Double presentVolumeOfPetrol) {
        this.presentVolumeOfPetrol = presentVolumeOfPetrol;
    }

    public Double getTheSubtractedLiterByThirty() {
        return theSubtractedLiterByThirty;
    }

    public void setTheSubtractedLiterByThirty(Double theSubtractedLiterByThirty) {
        this.theSubtractedLiterByThirty = theSubtractedLiterByThirty;
    }

    public List<NsukkaPetrolStations> getListOfNsukkaPetrolStations() {
        return listOfNsukkaPetrolStations;
    }

    public void setListOfNsukkaPetrolStations(List<NsukkaPetrolStations> listOfNsukkaPetrolStations) {
        this.listOfNsukkaPetrolStations = listOfNsukkaPetrolStations;
    }

    public double getTheDoubleValueOfCapacity() {
        return theDoubleValueOfCapacity;
    }

    public void setTheDoubleValueOfCapacity(double theDoubleValueOfCapacity) {
        this.theDoubleValueOfCapacity = theDoubleValueOfCapacity;
    }
    
   public NsukkaPetrolStations getNsukkaPetrolStationEntity() {
        return nsukkaPetrolStationEntity;
    }

    public void setNsukkaPetrolStationEntity(NsukkaPetrolStations nsukkaPetrolStationEntity) {
        this.nsukkaPetrolStationEntity = nsukkaPetrolStationEntity;
    }
    
}
