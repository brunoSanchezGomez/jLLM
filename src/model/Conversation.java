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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sanch
 */
public class Conversation implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    private String llmName;
    private List<Message> messages;
    private Instant startingDate;
    private Instant endDate;

    public Conversation(String llmName) {
        this.llmName = llmName;
        
        messages = new ArrayList<>();
        startingDate = Instant.now();
    }
    
    public void addMessage(Message message)  {
        messages.add(message);
    }
    
    public void endConversation(){
        endDate = Instant.now();
    }
    
    public String getConversationPreview()  {
        return String.format("%d | %2d mensajes | %20s",
                startingDate.getEpochSecond(), messages.size(), messages.get(0).getContent());
    }
    
    private String getFormattedStartingDate()    {
        ZonedDateTime zonedStartingDate = startingDate.atZone(ZoneId.systemDefault());
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
    
}
