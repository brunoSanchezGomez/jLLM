/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Model;
import view.ApplicationView;

/**
 *
 * @author sanch
 */
public class Controller {
    
    private ApplicationView view;
    private Model model;
    
    public Controller(ApplicationView view, Model model){
        this.view = view;
        this.model = model;
        
        this.view.setController(this);
    }
    
}
