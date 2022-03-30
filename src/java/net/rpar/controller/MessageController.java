/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.rpar.entity.Messages;
import net.rpar.jpaejb.MessagesFacade;
import net.rpar.model.bean.MessagesBean;

/**
 *
 * @author angelis
 */
@Named (value="messageController")
@SessionScoped
public class MessageController implements Serializable {
    
    @EJB
    MessagesFacade messageFacde;
    
    @Inject
    MessagesBean messageBean;

    
    public void addMessages(){
        try{
        Messages msg = new Messages();
        msg.setFullname(messageBean.getFullName());
        msg.setEmail(messageBean.getEmail());
        msg.setContent(messageBean.getContent());
        msg.setDate(new Date());
        
        messageFacde.create(msg);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Thank you. Your message has been sent"));
        cleanUpFields();
        }catch(Exception ex){
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry Your Message was not sent"));
        }
    }
    
    public void cleanUpFields(){
        messageBean.setFullName("");
        messageBean.setEmail("");
        messageBean.setContent("");
    }
    
    /** To get all Messages*/
    public List<Messages> getAllMessages(){
        return messageFacde.findAll();
    }
    
    /** To delete each message*/
    public void deleteMessage(Messages m){
        messageFacde.remove(m);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Message deleted"));
    }
    
    
    public MessagesFacade getMessageFacde() {
        return messageFacde;
    }

    public void setMessageFacde(MessagesFacade messageFacde) {
        this.messageFacde = messageFacde;
    }

    public MessagesBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessagesBean messageBean) {
        this.messageBean = messageBean;
    }
    
    
}
