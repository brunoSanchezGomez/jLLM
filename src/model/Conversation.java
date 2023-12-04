/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sanch
 */
class Conversation implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    private String llmName;
    private List<Message> messages;
}
