/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    
    // Atributos
    
    private static final long serialVersionUID = 2L;
    
    private String sender;
    private String timestamp;
    private String content;
    
    
    // Constructores
    
    public Message()    {
        // Necesario para Jackson
    }

    public Message(String sender) {
        this.sender = sender;
        
        timestamp = Instant.now().toString();
    }
    
    public Message(String sender, String content)   {
        this.sender = sender;
        this.content = content;
        
        timestamp = Instant.now().toString();
    }
    
    
    // Metodos
    
    @JsonIgnore
    private String getFormattedTimestamp() {
        ZonedDateTime zonedDateTime = Instant.parse(timestamp).atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return formatter.format(zonedDateTime);
    }
    
    @JsonIgnore
    public String getHeader()   {
        return String.format("%5s [%17s]: ", sender, getFormattedTimestamp());
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
    
    
    // Getters y setters

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
