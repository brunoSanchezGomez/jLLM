/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author sanch
 */
public class XmlRepository implements IRepository{
    
    private static final Path inputPath = Paths.get(System.getProperty("user.home"),
            "Desktop", "jLLM", "input.xml");
    private static final File inputFile = inputPath.toFile();
    
    private static final Path outputPath = Paths.get(System.getProperty("user.home"),
            "Desktop", "jLLM", "output.xml");
    private static final File outputFile = outputPath.toFile();
    
    private final XmlMapper xmlMapper;

    public XmlRepository() {
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public ArrayList<Conversation> importConversations() {
        if(inputFile.exists() && inputFile.isFile())    {
            try {
                String xml = new String(Files.readAllBytes(inputFile.toPath()), StandardCharsets.UTF_8);
                return xmlMapper.readValue(xml, xmlMapper.getTypeFactory().constructCollectionType(List.class, Conversation.class));
                
            }   catch (IOException e)   {
                System.err.println("Error al importar input.xml:" + e.getMessage());
                return null;
            }
        }   else    {
            return null;
        }
    }

    @Override
    public boolean exportConversations(List<Conversation> conversations) {
        try {
            String xml = xmlMapper.writeValueAsString(conversations);
            Files.write(outputFile.toPath(), xml.getBytes(StandardCharsets.UTF_8));
            return true;
            
        }   catch (JsonProcessingException e)  {
            System.err.println("Error al exportar output.xml:" + e.getMessage());
            return false;
            
        }   catch (IOException e)  {
            System.err.println("Error al exportar output.xml:" + e.getMessage());
            return false;
        }
    }
    
}
