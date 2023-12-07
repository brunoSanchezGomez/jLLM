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
    
    public Model(IRepository repository, ILLM llm){
        this.repository = repository;
        this.llm = llm;
        
        conversations = new ArrayList<>();
        serializationFile = Paths.get(System.getProperty("user.home"),
                "Desktop", "jLLM", "model.bin").toFile();
    }
    
    public void exportConversations()   {
        repository.exportConversations(conversations);
    }
    
    public void importConversations()   {
        ArrayList<Conversation> importedConversations = repository.importConversations();
        
        for(Conversation conversation : importedConversations)  {
            if(!conversations.contains(conversation))   {
                conversations.add(conversation);
            }
        } 
    }
    
    public boolean addConversation(Conversation conversation) {
        if (!conversations.contains(conversation)) {
            conversations.add(conversation);
            return true;
        }   else    {
            return false;
        }
    }
    
    public void removeConversation(int index) {
        conversations.remove(index);
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
