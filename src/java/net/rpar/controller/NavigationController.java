/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aaron
 */

@Named(value="navigationController")
@SessionScoped
public class NavigationController implements Serializable {
    
    public String toFeedBack(){
        return "feedback?faces-redirect=true";
    }
    
    public String toHome(){
        return"home?faces-redirect=true";
    }
    
    public String toAdminPage(){
        return "/forAdmin/admin?faces-redirect=true";
    }
 
    //=============================================
            //Navigation to and away from admin page
    //--------------------------------------------
    
    public String toAdmin(){
         return "/forAdmin/admin?faces-redirect=true";
    } 
    
    public String fromAdminToHome(){
        return "/home?faces-redirect=true";
    }
   
    //===================================================
        //  Navigation  from admin to the various links in home page
    //------------------------------------------------------
    
    public String fromAdminToFeedBack(){
        return "/feedback?faces-redirect=true";
    }
    
    //========================================================
    
    public String logOut(){
        HttpSession session= (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "home?faces-redirect=true";
    }
    
}
