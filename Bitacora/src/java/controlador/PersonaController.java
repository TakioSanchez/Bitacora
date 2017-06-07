/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PersonaCRUD;
import entidades.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.awt.X11.XConstants;

/**
 *
 * @author sanchez
 */
public class PersonaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
        
        // Sesiones
        HttpSession sesion = request.getSession(false);
        //String nombre_usuario = (String) sesion.getAttribute("nombre_usuario");
        //String rol = (String) sesion.getAttribute("rol");
        //if (nombre_usuario.equals("")) {
            //response.sendRedirect("/inicio/administrador.jsp");
        //} else if (rol.equals("Administrador") || rol.equals("Empleado") || rol.equals("Vendedor")) {
            //Acción a realizar
            String action = request.getParameter("action");
            switch (action) {
                /**
                 * *************** Respuestas a métodos POST
                 * *********************
                 */
                case "insertar":
                    insertarPersona(request, response, action);
                    
                    break;
                case "actualizar":
                    actualizarPersona(request, response, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    //insertarPersona(request, response, action);
                    break;
                case "listar":
                    listarPersonas(request, response, action);
                    break;
                case "modificar":
                    modificarPersona(request, response, action);
                    break;
                case "eliminar":
                    eliminarPersona(request, response, action);
                    break;
                case "buscar_persona":
                    //buscarPersona(request, response, sesion, action);
            }
        //} else {
        //    try {
        //        sesion.invalidate();
        //    } catch (Exception e) {
        //        System.out.println(e);
        //        response.sendRedirect("/inicio/administrador.jsp");
        //    }
        //    response.sendRedirect("/inicio/administrador.jsp");
        //}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void insertarPersona(HttpServletRequest request, HttpServletResponse response, String action) {
        Persona persona = extraerPersonaForm(request);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        int resultado;
        try {
            resultado = personaCRUD.registrarPersona(persona);
            //System.err.println("Resultado "+resultado);
            //enviar mensaje -> registrado
            if (resultado == 1){
                response.sendRedirect("/Bitacora/PersonaController?action=listar");
            }else{
                //if (resultado == 0){
                    response.sendRedirect("/Bitacora/error/error.jsp");
                    //request.setAttribute('respuesta', );
                //}else{
                    //response.sendRedirect("/Bitacora/error/error.jsp");
                //}
            }
        } catch (Exception ex) {
            listarPersonas(request, response, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Persona extraerPersonaForm(HttpServletRequest request) {
        Persona persona = new Persona();
        persona.setId_persona(request.getParameter("id_persona"));
        persona.setNombre_persona(request.getParameter("nombre_persona"));
        persona.setApellidos_persona(request.getParameter("apellidos_persona"));
        persona.setRol(request.getParameter("rol"));
        return persona;
    }
    
    private void listarPersonas(HttpServletRequest request, HttpServletResponse response, String action) {
        List<Persona> listaPersonas;
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            listaPersonas = (List<Persona>) personaCRUD.listarPersona();
            mostrarPersonas(request, response, listaPersonas, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarPersonas(HttpServletRequest request, HttpServletResponse response, List<Persona> listaPersonas, String action) {
        request.setAttribute("listaPersonas", listaPersonas);
        RequestDispatcher view = request.getRequestDispatcher("/persona/listarPersonas.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de personas");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void eliminarPersona(HttpServletRequest request, HttpServletResponse response, String action) {
        String id = request.getParameter("id_persona");
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.eliminarPersona(id);
            listarPersonas(request, response, action);
        } catch (Exception ex) {
            listarPersonas(request, response, "error_eliminar");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void modificarPersona(HttpServletRequest request, HttpServletResponse response, String action) {
        Persona personaEC = new Persona();
        personaEC.setId_persona(request.getParameter("id_persona"));
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            Persona persona = (Persona) personaCRUD.modificarPersona(personaEC);
            request.setAttribute("persona", persona);
            RequestDispatcher view = request.getRequestDispatcher("/persona/actualizarPersona.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPersonas(request, response, "error_modificar");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarPersona(HttpServletRequest request, HttpServletResponse response, String action) {
        Persona persona = extraerPersonaForm(request);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.actualizarPersona(persona,persona.getId_persona());
            listarPersonas(request, response, action);
        } catch (Exception ex) {
            listarPersonas(request, response, "error_actualizar");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
