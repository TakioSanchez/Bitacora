/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.SalaCRUD;
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
                //insertarSala(request, response, action);
                break;
            case "actualizar":
                //actualizarSala(request, response, action);
                break;
            /**
             * *************** Respuestas a métodos GET *********************
             */
            case "nuevo":
                capturarSala(request, response);
                break;
            case "listar":
                listarSalas(request, response, action);
                break;
            case "modificar":
                //modificarSala(request, response);
                break;
            case "eliminar":
                //eliminarSala(request, response);
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
    
    private void listarSalas(HttpServletRequest request, HttpServletResponse response, String action) {
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
        inyectarAtributos(request, "Modificar sala", "sala/nuevoSala.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error al mostrar la vista: sala/nuevoSala.jsp");
            System.out.println(ex);
        }
    }
}
