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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sanch
 */
public class Conversation implements Serializable{
    
    // Atributos
    
    private static final long SerialVersionUID = 2L;
    
    private String llmName;
    private List<Message> messages;
    private String startingDate;
    private String endDate;
    
    
    // Constructores
    
    public Conversation()   {
        // Necesario para Jackson
    }

    public Conversation(String llmName) {
        this.llmName = llmName;
        
        messages = new ArrayList<>();
        startingDate = Instant.now().toString();
    }
    
    
    // Metodos
    
    public void addMessage(Message message)  {
        messages.add(message);
    }
    
    public void endConversation(){
        endDate = Instant.now().toString();
    }    
    
    @JsonIgnore
    public String getConversationPreview()  {
        return String.format("%s | %2d mensajes | %20s",
                Instant.parse(startingDate).getEpochSecond(), messages.size(), messages.get(0).getContent());
    }
    
    @JsonIgnore
    private String getFormattedStartingDate()    {
        ZonedDateTime zonedStartingDate = Instant.parse(startingDate).atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return formatter.format(zonedStartingDate);
    }
    
    @Override
    public String toString()    {
        StringBuilder stringbuilder = new StringBuilder();
        
        stringbuilder.append(String.format("Conversacion del %17s :\n", getFormattedStartingDate()));
        
        for(Message message : messages) {
            stringbuilder.append(message.toString());
        }
        return stringbuilder.toString();
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
        
        final Conversation otherConversation = (Conversation) obj;
        
        return messages.equals(otherConversation.messages);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + messages.hashCode();
        return hash;
    }
    
    
    // Getters y setters

    public String getLlmName() {
        return llmName;
    }

    public void setLlmName(String llmName) {
        this.llmName = llmName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    
}
