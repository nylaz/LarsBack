/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author LasseLasseViking
 */
@Entity
public class Message implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromUser;
    private String toChannel;
    private String message;
    private Timestamp currentTimestamp;

    public String getFrom() {
        return fromUser;
    }

    public void setFrom(String from) {
        this.fromUser = from;
    }

    public String getTo() {
        return toChannel;
    }

    public void setTo(String to) {
        this.toChannel = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return currentTimestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.currentTimestamp = timestamp;
    }
}
