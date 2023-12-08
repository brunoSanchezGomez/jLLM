/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sanch
 */
public class JsonRepository implements IRepository{

    private static final Path inputPath = Paths.get(System.getProperty("user.home"),
            "Desktop", "jLLM", "input.json");
    private static final File inputFile = inputPath.toFile();
    
    private static final Path outputPath = Paths.get(System.getProperty("user.home"),
            "Desktop", "jLLM", "output.json");
    private static final File outputFile = outputPath.toFile();
    
    private final Gson gson;

    public JsonRepository() {
        this.gson = new Gson();
    }
    
    @Override
    public ArrayList<Conversation> importConversations() {
        if(inputFile.exists() && inputFile.isFile())    {
            try {
                String json = new String(Files.readAllBytes(inputFile.toPath()), StandardCharsets.UTF_8);
                Type listType = new TypeToken<ArrayList<Conversation>>() {}.getType();
                return gson.fromJson(json, listType);
            }   catch(IOException e)    {
                System.err.println("Error al importar input.json: " + e.getMessage());
                return null;
            }
        }   else    {
            return null;
        }
    }

    @Override
    public boolean exportConversations(List<Conversation> conversations) {
        try {
            String json = gson.toJson(conversations);
            Files.write(outputFile.toPath(), json.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar a output.json:" + e.getMessage());
            return false;
        }
    }
    
}
