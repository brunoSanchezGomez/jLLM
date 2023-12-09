/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Model;
import model.Message;
import view.ApplicationView;

/**
 *
 * @author sanch
 */
public class Controller {
    
    private final Model model;
    private final ApplicationView view;
    
    private Message userMessage;
    
    public Controller(Model model, ApplicationView view){
        this.model = model;
        this.view = view;
        
        this.view.setController(this);
    }

    public void initAplication() {
        
        if(model.loadAppState())    {
            view.showApplicationStart("Cargado estado anterior con éxito.");
        }   else    {
            view.showApplicationStart("No se encontró fichero para carga del programa.");
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
        
        if(!input.equals("/salir")) {
            String response = model.getResponse(input);
            Message responseMessage = new Message("Agent", response);
            model.addMessageToCurrentConversation(responseMessage);
            return String.format("%s%s", responseMessage.getHeader(),response);
        }
        return "";
    }
    
    public String getConversationString(int index)  {
        return model.getConversationString(index);
    }

    public int getConversationsNumber() {
        return model.getConversationsNumber();
    }

    public String getConversationPreview(int index) {
        return model.getConversationPreview(index);
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
