/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sanch
 */
public class Phrase {
    private String type;
    private int length;
    private String content;

    public Phrase(String type, int length, String content) {
        this.type = type;
        this.length = length;
        this.content = content;
    }
    
    public Phrase(String type, String content) {
        this.type = type;
        this.content = content;
    }
    
    public static Phrase getPhraseFromDelimitedString(String delimitedString, String delimiter) {
        String[] chunks = delimitedString.split(delimiter);
        
        if(chunks.length != 3)  {
            return null;
        }
        
        try {
            String type = chunks[0];
            int length = Integer.parseInt(chunks[1]);
            String content = chunks[2].trim();
            return new Phrase(type, length, content);
        } catch(NumberFormatException e)    {
            return null;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
