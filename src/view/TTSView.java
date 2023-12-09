/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import static com.coti.tools.Esdia.*;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngine;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngineNative;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import io.github.jonelo.jAdapterForNativeTTS.engines.Voice;
import io.github.jonelo.jAdapterForNativeTTS.engines.exceptions.SpeechEngineCreationException;
import java.io.IOException;
/**
 *
 * @author sanch
 */
public class TTSView extends ApplicationView{

    // Atributos
    
    private SpeechEngine speechEngine;
    
    
    // Constructor
    
    public TTSView()    {
        try {
            this.speechEngine = SpeechEngineNative.getInstance();
            
            VoicePreferences voicePreferences = new VoicePreferences();
            voicePreferences.setLanguage("es");
            voicePreferences.setCountry("ES");
            
            Voice voice = speechEngine.findVoiceByPreferences(voicePreferences);
            
            if(voice == null)   {
                voice = speechEngine.getAvailableVoices().get(0);
            }
            
            speechEngine.setVoice(voice.getName());
            
        } catch (SpeechEngineCreationException ex) {
            System.err.println("Error al crear el motor de narración por voz: " + ex.getMessage());
        }
    }
    
    private void narrate(String string) {
        try {
            speechEngine.say(string);
            Thread.sleep(100*string.length());
        }   catch(IOException ex)   {
            System.err.println("Error al narrar por voz: " + ex.getMessage());
        }   catch (InterruptedException ex) {
            System.err.println("Interrupción inesperada durante la narración: " + ex.getMessage());
        }
    }
    
    private void narrate(String string, int extraDelay) {
        try {
            speechEngine.say(string);
            Thread.sleep(100*string.length() + extraDelay);
        }   catch(IOException ex)   {
            System.err.println("Error al narrar por voz: " + ex.getMessage());
        }   catch (InterruptedException ex) {
            System.err.println("Interrupción inesperada durante la narración: " + ex.getMessage());
        }
    }
    
    @Override
    public void showApplicationStart(String initInfo) {
        System.out.println(initInfo);
        narrate(initInfo, 250);
    }

    @Override
    public void showMainMenu() {
        
        int option;
        do {
            System.out.println("\n--- MENÚ jLLM ---");
            narrate("MENÚ jLLM", 750);
            System.out.println("1. Nueva conversación");
            narrate("1. Nueva conversación", -500);
            System.out.println("2. Menú CRUD");
            narrate("2. Menú CRUD");
            System.out.println("3. Menú exportación");
            narrate("3. Menú exportacion");
            System.out.println("4. Salir");
            narrate("4. Salir");
            
            narrate("Ingrese una opción: ", -750);
            option = readInt("Ingrese una opción: ");

            switch (option) {
                case 1:
                    startConversation();
                    break;
                    
                case 2:
                    CRUDMenu();
                    break;
                    
                case 3:
                    exportMenu();
                    break;
                    
                case 4:
                    System.out.println("Saliendo...");
                    narrate("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
                    narrate("Opción no válida.");
                    break;
            }
        } while (option != 4);
    }
    
    @Override
    public void showApplicationEnd(String endInfo) {
        System.out.println(endInfo);
        
        narrate(endInfo);
    }
    
    private void startConversation() {
        System.out.println("\n--- NUEVA CONVERSACIÓN ---");
        
        narrate("NUEVA CONVERSACIÓN");
        
        String input;
        String response;
        
        do {
            input = readString_ne(c.startMessage());
            response = c.getResponse(input);
            System.out.println(response);
            
            narrate(response, response.length()*100);
            
        } while (!input.equals("/salir"));
        
        c.endConversation();
        System.out.println("Fin de la conversación.");
        
        narrate("Fin de la conversación.");
    }
    
    private void CRUDMenu()  {
        int option;
        do {
            System.out.println("\n--- MENÚ CRUD jLLM ---");
            narrate("MENÚ CRUD jLLM", 1000);
            System.out.println("1. Listar conversaciones");
            narrate("1. Listar conversaciones");
            System.out.println("2. Eliminar conversación");
            narrate("2. Eliminar conversación");
            System.out.println("3. Salir");
            narrate("3. Salir");
            
            narrate("Ingrese una opción: ", -750);
            option = readInt("Ingrese una opción: ");

            switch (option) {
                case 1:
                    listConversations();
                    break;
                    
                case 2:
                    removeConversation();
                    break;
                    
                case 3:
                    System.out.println("Saliendo...");
                    narrate("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
                    narrate("Opción no válida.");
                    break;
            }
        } while (option != 3);
    }
    
    private void listConversations() {
        System.out.println("\n--- LISTADO DE CONVERSACIONES ---");
        narrate("LISTADO DE CONVERSACIONES");
        
        int conversationsNumber = c.getConversationsNumber();
        
        String conversationPreview;
        if(conversationsNumber > 0) {
            for(int i = 0; i < conversationsNumber; i++)    {
                conversationPreview = String.format("%d. %s\n", i+1, c.getConversationPreview(i));
                System.out.printf(conversationPreview);
                narrate(conversationPreview, conversationPreview.length());
            }

            narrate("Desea consultar una de las conversaciones (y/n)?");
            if(yesOrNo("\nDesea consultar una de las conversaciones")) {
                narrate("Introduzca el índice de la conversación: ");
                int index = readInt("Introduzca el índice de la conversación: ");
                
                String conversationString = c.getConversationString(index-1);
                System.out.println("\n" + conversationString);
                narrate(conversationString, conversationString.length()*120);
            }
        }   else    {
            System.out.println("\nTodavía no hay ninguna conversación.");
            narrate("Todavía no hay ninguna conversación.");
        }
    }
    
    private void removeConversation()    {
        System.out.println("\n--- ELIMINAR CONVERSACION ---");
        narrate("ELIMINAR CONVERSACION");
        
        narrate("Introduzca el índice de la conversación que desea eliminar: ");
        int index = readInt("Introduzca el índice de la conversación que desea eliminar: ");
        
        if(c.removeConversation(index-1)) {
            System.out.println("Conversación eliminada con éxito.");
            narrate("Conversación eliminada con éxito.");
        }   else    {
            System.out.println("No existe una conversación con ese índice.");
            narrate("No existe una conversación con ese índice.");
        }
    }
    
    private void exportMenu()    {
        int option;
        do {
            System.out.println("\n--- MENÚ EXPORTACIÓN jLLM ---");
            narrate("MENÚ EXPORTACIÓN jLLM", 750);
            System.out.println("1. Exportar conversaciones");
            narrate("1. Exportar conversaciones");
            System.out.println("2. Importar conversaciones");
            narrate("2. Importar conversaciones");
            System.out.println("3. Salir");
            narrate("3. Salir");
            
            narrate("Ingrese una opción: ", -750);
            option = readInt("Ingrese una opción: ");

            switch (option) {
                case 1:
                    exportConversations();
                    break;
                case 2:
                    importConversations();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    narrate("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    narrate("Opción no válida.");
                    break;
            }
        } while (option != 3);
    }
    
    private void exportConversations()   {
        if(c.exportConversations()) {
            System.out.println("Exportación realizada con éxito.");
            narrate("Exportación realizada con éxito.");
        }   else    {
            System.out.println("Error al exportar.");
            narrate("Error al exportar.");
        }
    }
    
    private void importConversations()   {
        if(c.importConversations()) {
            System.out.println("Importación realizada con éxito.");
            narrate("Importación realizada con éxito.");
        }   else    {
            System.out.println("Error al importar.");
            narrate("Error al importar.");
        }
    }
}
