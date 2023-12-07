/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sanch
 */
public class MemoryLLM implements ILLM{
    
    private List<Phrase> phrasePool;

    public MemoryLLM()  {
        phrasePool = new ArrayList<>();
        
        phrasePool.add( new Phrase("saludo", "Hola!") );
        phrasePool.add( new Phrase("saludo", "Buenas, como estas?") );
        phrasePool.add( new Phrase("despedida", "Adios!") );
        phrasePool.add( new Phrase("despedida", "Hasta pronto!") );
        phrasePool.add( new Phrase("refran", "A quien madruga, Dios le ayuda!") );
        phrasePool.add( new Phrase("refran", "En boca cerrada no entran moscas!") );
        phrasePool.add( new Phrase("pregunta", "Se te da bien la programacion?") );
        phrasePool.add( new Phrase("pregunta", "Te gusta la Navidad?") );
        phrasePool.add( new Phrase("afirmacion", "Estoy de acuerdo!") );
        phrasePool.add( new Phrase("afirmacion", "Seguramente si!") );
        phrasePool.add( new Phrase("negacion", "Probablemente no.") );
        phrasePool.add( new Phrase("negacion", "Eso es imposible!") );
        phrasePool.add( new Phrase("sorpresa", "Impresionante!") );
        phrasePool.add( new Phrase("sorpresa", "No me lo esperaba!") );
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
        return "MemoryLLM";
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
