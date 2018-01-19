/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author LasseLasseViking
 */
@Stateless(name = "PersistenceManager")
public class PersistenceManager {
    
    @PersistenceContext(name = "persistencemanager")
    EntityManager em;
    
    public void createChatUser(ChatUser user) {
        em.persist(user);
        em.flush();
    }
    
    public ChatUser getSingleChatUser(String payload) {
        TypedQuery<ChatUser> query = em.createQuery(("SELECT u FROM ChatUser u WHERE u.userName='" + payload + "'"), ChatUser.class);
        if(!query.getResultList().isEmpty()) {
            ChatUser user = query.getSingleResult();
            return user;
        } else {
            ChatUser user = new ChatUser();
            return user;
        }
    }
    
    public List<ChatUser> getChatUsers(){
        TypedQuery<ChatUser> query = em.createQuery("SELECT u FROM ChatUser u", ChatUser.class);
        if(!query.getResultList().isEmpty()) {
            List<ChatUser> users = query.getResultList();
            return users;
        } else {
            List<ChatUser> users = new ArrayList<>();
            return users;
        }
    }
    
    public void updateChatUser(ChatUser user) {
        TypedQuery<ChatUser> query = em.createQuery("UPDATE ChatUser SET fullName = '"+ user.getFullName() +"', info = '"+ user.getInfo() +"', image = '" + user.getImage() +"' WHERE userName = '" + user.getUserName() +"'", ChatUser.class);
        query.executeUpdate();
    }
    
    public ChatRoom getChatroomMetadata(int id) {
        TypedQuery<ChatRoom> query = em.createQuery(("SELECT c FROM ChatRoom c WHERE c.id='"+ id + "'"), ChatRoom.class);
        return query.getSingleResult();
    }
    
    public void createChatRoom(ChatRoom payload) {
            em.persist(payload);
            em.flush();
    }

    public List<ChatRoom> getAllChats() {
        TypedQuery<ChatRoom> query = em.createQuery("SELECT r FROM ChatRoom r", ChatRoom.class);
        if(!query.getResultList().isEmpty()) {
            List<ChatRoom> rooms = query.getResultList();
            return rooms;
        } else {
            List<ChatRoom> rooms = new ArrayList<>();
            return rooms;
        }
    }

    public List<Message> getAllMessages(int channel) {
        TypedQuery<Message> query = em.createQuery(("SELECT m FROM Message m WHERE m.toChannel='" + channel + "'"), Message.class);
        if(!query.getResultList().isEmpty()) {
            List<Message> msg = query.getResultList();
            return msg;
        } else {
            List<Message> msg = new ArrayList<>();
            return msg;
        }
    }

    public void postMessage(Message message) {
        em.persist(message);
        em.flush();
    }
}
