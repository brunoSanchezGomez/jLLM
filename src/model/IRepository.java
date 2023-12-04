/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author sanch
 */
public interface IRepository {
    
    public List<Conversation> importConversation();
    
    public void exportConversation(List<Conversation> conversations);
}
