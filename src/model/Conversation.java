/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sanch
 */
class Conversation implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    private String llmName;
    private List<Message> messages;
    private Instant startingDate;
    private Instant endDate;

    public Conversation(String llmName, List<Message> messages) {
        this.llmName = llmName;
        this.messages = messages;
        
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
    
    public String getFormattedStartingDate()    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return formatter.format(startingDate);
    }
    
    public String getConversationHeader()   {
        return String.format("Conversacion del %17s :", getFormattedStartingDate());
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
