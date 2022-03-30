/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.login;

import net.rpar.model.bean.UserBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.rpar.entity.Users;
import net.rpar.jpaejb.UsersFacade;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Aaron
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String password;
    private UserBean userPresence;
    private boolean islogged;
    Object userSession;
    
    @EJB
    UsersFacade userFacade;

    Users dbUser;

    @Inject
    UserBean userbean;
    
    @Inject
    Login login;

    // To login from db
    private List<Users> listOfUsers;
    
    //==================================================================================
             // login with crypto
    //-------------------------------------------------------------
    
    public String findUserFromDbOne2() {
        try {
            listOfUsers = userFacade.findAll();
            for (Users u : listOfUsers) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (login.getEmail().equals(u.getEmail()) && passwordEncoder.matches(login.getPassword(), u.getPassword())){
                    dbUser = new Users(u.getFullname());
                    islogged = true;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("theUser", email);
                    return "/forAdmin/admin.jsf?faces-redirect=true";
                } else {
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalide username or password"));
        return "home.jsf";
    }
    
    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("theUser");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        userPresence = null;
        dbUser = null;
        islogged = false;
        return "/home?faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserBean getUserPresence() {
        return userPresence;
    }

    public void setUserPresence(UserBean userPresence) {
        this.userPresence = userPresence;
    }

    public List<Users> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<Users> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public boolean isIslogged() {
        return islogged;
    }

    public void setIslogged(boolean islogged) {
        this.islogged = islogged;
    }

    public UsersFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UsersFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Users getDbUser() {
        return dbUser;
    }

    public void setDbUser(Users dbUser) {
        this.dbUser = dbUser;
    }

    public UserBean getUserbean() {
        return userbean;
    }

    public void setUserbean(UserBean userbean) {
        this.userbean = userbean;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Object getUserSession() {
        return userSession;
    }

    public void setUserSession(Object userSession) {
        this.userSession = userSession;
    }

}
