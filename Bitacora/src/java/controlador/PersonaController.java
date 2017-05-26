/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String nombre_usuario = (String) sesion.getAttribute("nombre_usuario");
        String rol = (String) sesion.getAttribute("rol");
        if (nombre_usuario.equals("")) {
            response.sendRedirect("/inicio/administrador.jsp");
        } else if (rol.equals("Administrador") || rol.equals("Empleado") || rol.equals("Vendedor")) {
            //Acción a realizar
            String action = request.getParameter("action");
            switch (action) {
                /**
                 * *************** Respuestas a métodos POST
                 * *********************
                 */
                case "insertar":
                    //registrarPersona(request, response, sesion, action);
                    break;
                case "actualizar":
                    //actualizarPersona(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    //prepararNuevoPersona(request, response, sesion);
                    break;
                case "listar":
                    //listarPersonas(request, response, sesion, action);
                    break;
                case "modificar":
                    //modificarPersona(request, response, sesion, action);
                    break;
                case "eliminar":
                    //eliminarPersona(request, response, sesion, action);
                    break;
                case "buscar_persona":
                    //buscarPersona(request, response, sesion, action);
            }
        } else {
            try {
                sesion.invalidate();
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("/inicio/administrador.jsp");
            }
            response.sendRedirect("/inicio/administrador.jsp");
        }
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
    
    /*private void listarPersonas(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Persona> listaPersonas;
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            listaPersonas = (List<Persona>) personaCRUD.listarPersona((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPersonas(request, response, listaPersonas, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
