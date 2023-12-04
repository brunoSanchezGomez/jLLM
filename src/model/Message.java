/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author sanch
 */
class Message implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String sender;
    private long epochSeconds;
    private String content;
}
