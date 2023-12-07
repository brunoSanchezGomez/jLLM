/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Model;
import model.Message;
import view.ApplicationView;

/**
 *
 * @author sanch
 */
public class Controller {
    
    private Model model;
    private ApplicationView view;
    
    private Message userMessage;
    
    public Controller(Model model, ApplicationView view){
        this.model = model;
        this.view = view;
        
        this.view.setController(this);
    }

    public void initAplication() {
        
        if(model.loadAppState())    {
            view.showApplicationStart("Cargado estado anterior con exito");
        }   else    {
            view.showApplicationStart("No se encontró fichero para carga del programa");
        }
        
        view.showMainMenu();
        
        if(model.saveAppState())    {
            view.showApplicationEnd("Guardado el estado de la aplicación.\nSaliendo...");
        }   else    {
            view.showApplicationEnd("No se pudo guardar el estado de la aplicación.\nSaliendo...");
        }
    }

    public String startMessage()    {
        userMessage = new Message("Yo");
        return userMessage.getHeader();
    }

    public String getResponse(String input) {
        userMessage.setContent(input);
        model.addMessageToCurrentConversation(userMessage);
        
        String response = model.getResponse(input);
        model.addMessageToCurrentConversation(new Message("Agent", response));
        return response;
    }

    public int getConversationsNumber() {
        return model.getConversationsNumber();
    }

    public String getConversationHeader(int index) {
        return model.getConversationHeader(index);
    }

    public boolean removeConversation(int index) {
        return model.removeConversation(index);
    }

    public void endConversation() {
        model.endCurrentConversation();
    }

    public boolean exportConversations() {
        return model.exportConversations();
    }

    public boolean importConversations() {
        return model.importConversations();
    }
    
}
