/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PersonaCRUD;
import entidades.Persona;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanchez
 */
public class prueba {
    public static void main(String[] args){
        PersonaCRUD pc = new PersonaCRUD();
//        Persona p = new Persona("2012060161", "Mario", "Marcos Santiago", "Alumno");
        try {
//            System.out.println(pc.registrarPersona(p));
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}