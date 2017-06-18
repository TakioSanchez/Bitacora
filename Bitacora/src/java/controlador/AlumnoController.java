/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlumnoCRUD;
import dao.PersonaCRUD;
import entidades.Alumno;
import entidades.Persona;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sanchez
 */
@WebServlet(name = "AlumnoController", urlPatterns = {"/AlumnoController"})
public class AlumnoController extends HttpServlet {

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
                insertarAlumno(request, response, action);
                break;
            case "actualizar":
                actualizarAlumno(request, response, action);
                break;
            /**
             * *************** Respuestas a métodos GET *********************
             */
            case "nuevo":
                capturarAlumno(request, response);
                break;
            case "listar":
                listarAlumnos(request, response, action);
                break;
            case "modificar":
                modificarAlumno(request, response);
                break;
            case "eliminar":
                eliminarAlumno(request, response);
                break;
            case "buscar_Alumno":
            //buscarAlumno(request, response, sesion, action);
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

    private void listarAlumnos(HttpServletRequest request, HttpServletResponse response, String action) {
        List<Alumno> listaAlumnos;
        AlumnoCRUD alumnoCRUD = new AlumnoCRUD();
        try {
            listaAlumnos = (List<Alumno>) alumnoCRUD.listarAlumno();
            mostrarAlumnos(request, response, listaAlumnos);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void mostrarAlumnos(HttpServletRequest request, HttpServletResponse response, List<Alumno> listaAlumnos) {
        request.setAttribute("listaAlumnos", listaAlumnos);
        inyectarAtributos(request, "Listar alumnos", "alumno/listarAlumnos.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la vista: alumno/listarAlumnos.jsp");
        }
    }

    private void capturarAlumno(HttpServletRequest request, HttpServletResponse response) {
        inyectarAtributos(request, "Modificar alumno", "alumno/nuevoAlumno.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error al mostrar la vista: alumno/nuevoAlumno.jsp");
            System.out.println(ex);
        }
    }

    private void inyectarAtributos(HttpServletRequest request, String titulo, String pagina) {
        request.setAttribute("titulo", titulo + " | Bitácora");
        request.setAttribute("pagina", "../" + pagina);
    }

    private void insertarAlumno(HttpServletRequest request, HttpServletResponse response, String action) {
        Persona persona = extraerPersonaForm(request, action);
        Alumno alumno = extraerAlumnoForm(request, action);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        AlumnoCRUD alumnoCRUD = new AlumnoCRUD();
        int resultadop;
        int resultadoa;
        try {
            resultadop = personaCRUD.registrarPersona(persona);
            if (resultadop == 1) { // registrado
                resultadoa = alumnoCRUD.registrarAlumno(alumno);
                if (resultadoa == 1) {
                    response.sendRedirect("/Bitacora/AlumnoController?action=listar");
                }
            } else {//no registrado
                response.sendRedirect("/Bitacora/error/error.jsp");
            }
        } catch (Exception ex) {
            listarAlumnos(request, response, "error_registrar");
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

    private Alumno extraerAlumnoForm(HttpServletRequest request, String action) {
        Alumno alumno = new Alumno();
        if (action.equals("actualizar")) {
            alumno.setMatricula_anterior(request.getParameter("id_persona_anterior"));
        }
        alumno.setMatricula(request.getParameter("id_persona"));
        alumno.setNombre_persona(request.getParameter("nombre_persona"));
        alumno.setApellidos_persona(request.getParameter("apellidos_persona"));
        alumno.setCarrera(request.getParameter("carrera"));
        alumno.setSemestre(Integer.valueOf(request.getParameter("semestre")));
        alumno.setGrupo(request.getParameter("grupo"));
        return alumno;
    }

    private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) {
        Alumno alumnoEC = new Alumno();
        alumnoEC.setMatricula(request.getParameter("matricula"));
        AlumnoCRUD alumnoCRUD = new AlumnoCRUD();
        try {
            Alumno alumno = (Alumno) alumnoCRUD.buscarAlumno(alumnoEC);
            request.setAttribute("alumno", alumno);
            inyectarAtributos(request, "Modificar alumno", "alumno/actualizarAlumno.jsp");
            RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error al mostrar la vista: alumno/actualizaAlumno.jsp");
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar alumno");
            System.out.println(ex);
            listarAlumnos(request, response, "error_modificar");
        }

    }

    private void actualizarAlumno(HttpServletRequest request, HttpServletResponse response, String action) {
        Alumno alumno = extraerAlumnoForm(request, action);
        AlumnoCRUD alumnoCRUD = new AlumnoCRUD();
        try {
            alumnoCRUD.actualizarAlumno(alumno);
            response.sendRedirect("/Bitacora/AlumnoController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarAlumnos(request, response, "error_actualizar");
        }
    }
    
    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) {
        String matricula = request.getParameter("matricula");
        AlumnoCRUD alumnoCRUD = new AlumnoCRUD();
        System.out.println(matricula);
        try {
            alumnoCRUD.eliminarAlumno(matricula);
            response.sendRedirect("/Bitacora/AlumnoController?action=listar");//evita acciones repetidas
        } catch (Exception ex) {
            System.out.println(ex);
            listarAlumnos(request, response, "error_eliminar");
        }
    }
}
