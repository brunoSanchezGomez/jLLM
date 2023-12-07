/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sanch
 */
public class Message implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String sender;
    private Instant timestamp;
    private String content;

    public Message(String sender) {
        this.sender = sender;
        
        timestamp = Instant.now();
    }
    
    public Message(String sender, String content)   {
        this.sender = sender;
        this.content = content;
        
        timestamp = Instant.now();
    }
    
    private String getFormattedTimestamp() {
        ZonedDateTime zonedDateTime = timestamp.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return formatter.format(zonedDateTime);
    }
    
    public String getHeader()   {
        return String.format("%5s [%17s]: ", sender, getFormattedTimestamp());
    }
    
    public String getContent()  {
        return content;
    }
    
    public void setContent(String content)  {
        this.content = content;
    }
    
    @Override
    public String toString()    {
        return String.format("%5s [%17s]: %s\n", sender, getFormattedTimestamp(),content);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Message otherMessage = (Message) obj;
        
        return content.equals(otherMessage.content);
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 37 * hash + content.hashCode();
        return hash;
    }
    
}
