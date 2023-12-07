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
    
    private Model model;
    private ApplicationView view;
    
    public Controller(Model model, ApplicationView view){
        this.model = model;
        this.view = view;
        
        this.view.setController(this);
    }

    public void initAplication() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
