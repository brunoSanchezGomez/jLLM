/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import java.io.IOException;

/**
 *
 * @author sanch
 */
public class SmartLLM implements ILLM{
    
    // Atributos
    
    private final OllamaAPI ollamaAPI;
    
    
    // Constructor
    
    /**
     * Para utilizar SmartLLM es necesario generar un host de Ollama e
     * introducir su direccion como cuarto argumento al iniciar la aplicacion.
     * 
     * @param host 
     */
    public SmartLLM(String host)   {
        this.ollamaAPI = new OllamaAPI(host);
    }
    
    
    /// Metodos

    @Override
    public String speak(String input) {
        try {
            String response = ollamaAPI.ask("mistral", input);
            return response;
        } catch (OllamaBaseException | IOException | InterruptedException ex) {
            System.err.println("Error al acceder a ollamaAPI: " + ex.getMessage());
            return "";
        }
    }

    @Override
    public String getIdentifier() {
        return "SmartLLM";
    }
    
}
