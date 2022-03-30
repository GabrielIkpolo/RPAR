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

@Named(value="userBean")
@SessionScoped
public class UserBean  implements Serializable{
    
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private Date date;

   
    
    public UserBean(){
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public UserBean(String fullname) {
        this.fullname = fullname;
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

     public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
     public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "UserBean{" + "id=" + id + ", username=" + username + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", date=" + date + '}';
    }
    
}
