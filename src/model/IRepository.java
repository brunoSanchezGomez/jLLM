/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanch
 */
public interface IRepository {
    
    public ArrayList<Conversation> importConversations();
    
    public void exportConversations(List<Conversation> conversations);
}
