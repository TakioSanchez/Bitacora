package controlador;

import dao.PersonaCRUD;
import entidades.Persona;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//        HttpSession sesion = request.getSession(false);
        //String nombre_usuario = (String) sesion.getAttribute("nombre_usuario");
        //String rol = (String) sesion.getAttribute("rol");
        //if (nombre_usuario.equals("")) {
        //response.sendRedirect("/inicio/administrador.jsp");
        //} else if (rol.equals("Administrador") || rol.equals("Empleado") || rol.equals("Vendedor")) {
        //Acción a realizar
        String action = request.getParameter("action");
        switch (action) {
            /**
             * *************** Respuestas a métodos POST *********************
             */
            case "insertar":
                insertarPersona(request, response, action);
                break;
            case "actualizar":
                actualizarPersona(request, response, action);
                break;
            /**
             * *************** Respuestas a métodos GET *********************
             */
            case "nuevo":
                capturarPersona(request, response);
                break;
            case "listar":
                listarPersonas(request, response, action);
                break;
            case "modificar":
                modificarPersona(request, response);
                break;
            case "eliminar":
                eliminarPersona(request, response);
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
        Persona persona = extraerPersonaForm(request, action);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        int resultado;
        try {
            resultado = personaCRUD.registrarPersona(persona);
            if (resultado == 1) { // registrado
                response.sendRedirect("/Bitacora/PersonaController?action=listar");
            } else {//no registrado
                response.sendRedirect("/Bitacora/error/error.jsp");
            }
        } catch (Exception ex) {
            listarPersonas(request, response, "error_registrar");
            System.out.println(ex);
        }
    }

    private Persona extraerPersonaForm(HttpServletRequest request, String action) {
        Persona persona = new Persona();
        if (action.equals("actualizar")) {
            persona.setId_persona_anterior(request.getParameter("id_persona_anterior"));
        }
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
            mostrarPersonas(request, response, listaPersonas);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void mostrarPersonas(HttpServletRequest request, HttpServletResponse response, List<Persona> listaPersonas) {
        request.setAttribute("listaPersonas", listaPersonas);
        // atributos: request, titulo_pagina, pagina
        inyectarAtributos(request, "Listar personas", "persona/listarPersonas.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la vista: persona/listarPersonas.jsp");
        }
    }

    private void eliminarPersona(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id_persona");
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.eliminarPersona(id);
            response.sendRedirect("/Bitacora/PersonaController?action=listar");//evita acciones repetidas
        } catch (Exception ex) {
            System.out.println(ex);
            listarPersonas(request, response, "error_eliminar");
        }
    }

    private void modificarPersona(HttpServletRequest request, HttpServletResponse response) {
        Persona personaEC = new Persona();
        personaEC.setId_persona(request.getParameter("id_persona"));
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            Persona persona = (Persona) personaCRUD.buscarPersona(personaEC);
            request.setAttribute("persona", persona);
            inyectarAtributos(request, "Modificar persona", "persona/actualizarPersona.jsp");
            RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error al mostrar la vista: persona/actualizarPersona.jsp");
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar persona");
            System.out.println(ex);
            listarPersonas(request, response, "error_modificar");
        }

    }

    private void actualizarPersona(HttpServletRequest request, HttpServletResponse response, String action) {
        Persona persona = extraerPersonaForm(request, action);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.actualizarPersona(persona);
            response.sendRedirect("/Bitacora/PersonaController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarPersonas(request, response, "error_actualizar");
        }
    }

    private void inyectarAtributos(HttpServletRequest request, String titulo, String pagina) {
        request.setAttribute("titulo", titulo + " | Bitácora");
        request.setAttribute("pagina", "../" + pagina);
    }

    private void capturarPersona(HttpServletRequest request, HttpServletResponse response) {
        inyectarAtributos(request, "Modificar persona", "persona/nuevoPersona.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error al mostrar la vista: persona/nuevoPersona.jsp");
            System.out.println(ex);
        }
    }
}
