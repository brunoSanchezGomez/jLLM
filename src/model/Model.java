/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanch
 */
public class Model implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private transient IRepository repository;
    private transient ILLM llm;
    private transient File serializationFile;
    
    private List<Conversation> conversations;
    private Conversation currentConversation;
    
    public Model(IRepository repository, ILLM llm){
        this.repository = repository;
        this.llm = llm;
        
        conversations = new ArrayList<>();
        currentConversation = new Conversation(llm.getIdentifier());
        serializationFile = Paths.get(System.getProperty("user.home"),
                "Desktop", "jLLM", "model.bin").toFile();
    }
    
    public boolean exportConversations()   {
        return repository.exportConversations(conversations);
    }
    
    public boolean importConversations()   {
        ArrayList<Conversation> importedConversations = repository.importConversations();
        
        if(importedConversations != null)   {
            for(Conversation conversation : importedConversations)  {
                if(!conversations.contains(conversation))   {
                    conversations.add(conversation);
                }
            }
            return true;
        }   else  {
            return false;
        }
    }
    
    public void addMessageToCurrentConversation(Message message)  {
        currentConversation.addMessage(message);
    }
    
    public void endCurrentConversation() {
        currentConversation.endConversation();
        conversations.add(currentConversation);
        currentConversation = new Conversation(llm.getIdentifier());
    }
    
    public boolean removeConversation(int index) {
        if(index < conversations.size()){
            conversations.remove(index);
            return true;
        }   else    {
            return false;
        }
    }
    
    public String getResponse(String input) {
        return llm.speak(input);
    }
    
    public String getConversationHeader(int index)  {
        return conversations.get(index).getConversationHeader();
    }
    
    public int getConversationsNumber()  {
        return conversations.size();
    }
    
    public boolean loadAppState() {

        if (serializationFile.exists() && serializationFile.isFile()) {
            
            try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializationFile)) )    {
                conversations = (ArrayList<Conversation>) ois.readObject();
                
            } catch (IOException | ClassNotFoundException ex) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean saveAppState() {
        
        try( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializationFile)) )    {
            oos.writeObject(conversations);

        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
}
