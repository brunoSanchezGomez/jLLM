/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sanch
 */
public class CSVLLM implements ILLM{
    
    private List<Phrase> phrasePool;
    
    private final String delimiter = ",";
    
    public CSVLLM() {
        phrasePool = new ArrayList<>();
        
        Path CSVPath = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "phrases.csv");
        List<String> lines;
        
        try {
            lines = Files.readAllLines(CSVPath, StandardCharsets.UTF_8);
            phrasePoolFactory(lines);
        }   catch(IOException e)    {
            System.err.println("Error al importar phrases.csv: " + e.getMessage());
        }
    }

    @Override
    public String speak(String input) {
        String type = getType(input);
        
        if(type.equals("random"))   {
            return getRandomPhrase();
        }   else    {
            return getRandomPhraseByType(type);
        }
    }

    @Override
    public String getIdentifier() {
        return "CSVLLM";
    }
    
    private void phrasePoolFactory(List<String> lines)  {
        for(String line : lines)    {
            Phrase phrase = Phrase.getPhraseFromDelimitedString(line, delimiter);
            if(phrase != null)  {
                phrasePool.add(phrase);
            }
        }
    }
    
    private String getType(String input) {
        if(containsWord(input, "hola"))
            return "saludo";
        if(containsWord(input, "adios"))
            return "despedida";
        if(containsWord(input, "refran"))
            return "refran";
        if(containsWord(input, "pregunta"))
            return "pregunta";
        if(containsWord(input, "?"))
            return "respuesta";
        if(containsWord(input, "si"))
            return "afirmacion";
        if(containsWord(input, "no"))
            return "negacion";
        if(containsWord(input, "!"))
            return "sorpresa";
        return "random";
    }
    
    private boolean containsWord(String input, String word)  {
        return input.toLowerCase().contains(word.toLowerCase());
    }
    
    private String getRandomPhrase()    {
        Random random = new Random();
        int index = random.nextInt(phrasePool.size());
        return phrasePool.get(index).getContent();
    }
    
    private String getRandomPhraseByType(String type)    {
        List<Phrase> phrasePoolOfType = new ArrayList<>();
        for(Phrase phrase : phrasePool) {
            if(phrase.getType().equals(type))   {
                phrasePoolOfType.add(phrase);
            }
        }
        
        Random random = new Random();
        int index = random.nextInt(phrasePoolOfType.size());
        
        return phrasePoolOfType.get(index).getContent();
    }
}
