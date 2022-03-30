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
 * @author angelis
 */
@Named (value="messageBean")
@SessionScoped
public class MessagesBean implements Serializable {
    
    private int id;
    private String fullName;
    private String email;
    private String content;
    private Date date;

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
     @Override
    public String toString() {
        return "MessagesBean{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", content=" + content + ", date=" + date + '}';
    }
}
