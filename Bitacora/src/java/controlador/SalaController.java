/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PersonaCRUD;
import dao.RegistroCRUD;
import dao.SalaCRUD;
import entidades.Persona;
import entidades.Registro;
import entidades.Sala;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SalaController", urlPatterns = {"/SalaController"})
public class SalaController extends HttpServlet {

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
                insertarSala(request, response, action);
                break;
            case "actualizar":
                actualizarSala(request, response, action);
                break;
            /**
             * *************** Respuestas a métodos GET *********************
             */
            case "nuevo":
                capturarSala(request, response);
                break;
            case "listar":
                listarSalas(request, response);
                break;
            case "modificar":
                modificarSala(request, response);
                break;
            case "eliminar":
                eliminarSala(request, response);
                break;
            case "buscar_sala":
            //buscarSala(request, response, sesion, action);
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
    
    private void listarSalas(HttpServletRequest request, HttpServletResponse response) {
        List<Sala> listaSalas;
        SalaCRUD salaCRUD = new SalaCRUD();
        try {
            listaSalas = (List<Sala>) salaCRUD.listarSala();
            mostrarSalas(request, response, listaSalas);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void mostrarSalas(HttpServletRequest request, HttpServletResponse response, List<Sala> listaSalas) {
        request.setAttribute("listaSalas", listaSalas);
        // atributos: request, titulo_pagina, pagina
        inyectarAtributos(request, "Listar salas", "sala/listarSalas.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la vista: sala/listarSalas.jsp");
        }
    }
    
    private void inyectarAtributos(HttpServletRequest request, String titulo, String pagina) {
        request.setAttribute("titulo", titulo + " | Bitácora");
        request.setAttribute("pagina", "../" + pagina);
    }
    
    private void capturarSala(HttpServletRequest request, HttpServletResponse response) {
        List<Persona> listaPersonas = null;
        listaPersonas = listarPersonas(request, response);
        request.setAttribute("listaPersonas", listaPersonas);
        inyectarAtributos(request, "Modificar sala", "sala/nuevoSala.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error al mostrar la vista: sala/nuevoSala.jsp");
            System.out.println(ex);
        }
    }
    
    private List<Persona> listarPersonas(HttpServletRequest request, HttpServletResponse response) {
        List<Persona> listaPersonas = null;
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            listaPersonas = (List<Persona>) personaCRUD.listarPersonaA();
            //mostrarPersonas(request, response, listaPersonas);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listaPersonas;
    }
    
    private void insertarSala(HttpServletRequest request, HttpServletResponse response, String action) {
        SalaCRUD salaCRUD = new SalaCRUD();
        Sala sala = extraerSalaForm(request, action);
        System.out.println(sala.getId_sala());
        System.out.println(sala.getNombre_sala());
        System.out.println(sala.getNum_maquinas());
        System.out.println(sala.getId_encargado());
        try {
            salaCRUD.registrarSala(sala);
            response.sendRedirect("/Bitacora/SalaController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            try {
                response.sendRedirect("/Bitacora/error/error.jsp");
            } catch (IOException ex1) {
                System.out.println(ex1);
            }
        }
    }

    private Sala extraerSalaForm(HttpServletRequest request, String action) {
        Sala sala = new Sala();
        if (action.equals("actualizar")) {
            sala.setId_sala(Integer.valueOf(request.getParameter("id_sala").trim()));
        }
        sala.setNombre_sala(request.getParameter("nombre_sala").trim());
        sala.setNum_maquinas(Integer.valueOf(request.getParameter("num_maquinas").trim()));
        sala.setId_encargado(request.getParameter("id_encargado").trim());
        
        return sala;
    }
    
    private void modificarSala(HttpServletRequest request, HttpServletResponse response) {
        SalaCRUD salaCRUD = new SalaCRUD();
        List<Persona> listaPersonas = null;
        listaPersonas = listarPersonas(request, response);
        request.setAttribute("listaPersonas", listaPersonas);
        int id_sala = Integer.valueOf(request.getParameter("id_sala").trim());
        System.out.println(id_sala);
        try {
            Sala sala = (Sala) salaCRUD.buscarSala(id_sala);
            request.setAttribute("sala", sala);
            inyectarAtributos(request, "Modificar sala", "sala/actualizarSala.jsp");
            RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error al mostrar la vista: sala/actualizaSala.jsp");
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar sala");
            System.out.println(ex);
            listarSalas(request, response);
        }
    }
    
    private void actualizarSala(HttpServletRequest request, HttpServletResponse response, String action) {
        Sala sala = extraerSalaForm(request, action);
        String id_encargado = request.getParameter("id_encargado");
        SalaCRUD salaCRUD = new SalaCRUD();
        try {
            salaCRUD.actualizarSala(sala,id_encargado);
            response.sendRedirect("/Bitacora/SalaController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalas(request, response);
        }
    }
    
    private void eliminarSala(HttpServletRequest request, HttpServletResponse response) {
        SalaCRUD salaCRUD = new SalaCRUD();
        int id_sala = Integer.valueOf(request.getParameter("id_sala").trim());
        try {
            salaCRUD.eliminarSala(id_sala);
            response.sendRedirect("/Bitacora/SalaController?action=listar");//evita acciones repetidas
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalas(request, response);
        }
    }
}
