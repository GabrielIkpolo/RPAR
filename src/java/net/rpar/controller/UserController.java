/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.controller;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.rpar.entity.Users;
import net.rpar.jpaejb.UsersFacade;
import net.rpar.model.bean.UserBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Aaron
 */
@SessionScoped
@Named(value = "userController")
public class UserController implements Serializable {

    @EJB
    UsersFacade userFacade;

    @Inject
    UserBean userBean;
    
    public void addUser() {
        try {
            Users users = new Users();
            users.setUsername(userBean.getUsername());
            users.setFullname(userBean.getFullname());
            users.setEmail(userBean.getEmail());
            
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userBean.getPassword());
            users.setPassword(encodedPassword);
            
            users.setDate(new Date());
            
            userFacade.create(users);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added"));
            clearFieldsForAddUser();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry Something wrong happened. User NOT added"));
        }
    }

    private void clearFieldsForAddUser() {
        userBean.setUsername("");
        userBean.setFullname("");
        userBean.setEmail("");
        userBean.setPassword("");
    }

    // Getters and Setters
    public UsersFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UsersFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

}
