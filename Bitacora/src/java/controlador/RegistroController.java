package controlador;

import dao.RegistroCRUD;
import entidades.Registro;
import entidades.Sala;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lmarcoss
 */
public class RegistroController extends HttpServlet {

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
        String action = request.getParameter("action");
        switch (action) {
            /**
             * *************** Respuestas a métodos POST *********************
             */
            case "insertar":
                insertarRegistro(request, response, action);
                break;
            case "actualizar":
                actualizarRegistro(request, response, action);
                break;
            /**
             * *************** Respuestas a métodos GET *********************
             */
            case "nuevo":
                capturarRegistro(request, response);
                break;
            case "listar":
                listarRegistros(request, response);
                break;
            case "modificar":
                modificarRegistro(request, response);
                break;
            case "eliminar":
                eliminarRegistro(request, response);
                break;
            case "buscar_Registro":
            //buscarRegistro(request, response, sesion, action);
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

    private void listarRegistros(HttpServletRequest request, HttpServletResponse response) {
        List<Registro> listaRegistros;
        RegistroCRUD registroCRUD = new RegistroCRUD();
        try {
            listaRegistros = (List<Registro>) registroCRUD.listarRegistro();
            mostrarRegistros(request, response, listaRegistros);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void mostrarRegistros(HttpServletRequest request, HttpServletResponse response, List<Registro> listaRegistros) {
        request.setAttribute("listaRegistros", listaRegistros);
        inyectarAtributos(request, "Listar registros", "registro/listarRegistros.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la vista: registro/listarRegistros.jsp");
        }
    }

    private void capturarRegistro(HttpServletRequest request, HttpServletResponse response) {
        List <Sala> listaSalas =  null;
        request.setAttribute("listaSalas",listaSalas);
        inyectarAtributos(request, "Nuevo registro", "registro/nuevoRegistro.jsp");
        RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.out.println("Error al mostrar la vista: registro/nuevoRegistro.jsp");
        }
    }

    private void inyectarAtributos(HttpServletRequest request, String titulo, String pagina) {
        request.setAttribute("titulo", titulo + " | Bitácora");
        request.setAttribute("pagina", "../" + pagina);
    }

    private void insertarRegistro(HttpServletRequest request, HttpServletResponse response, String action) {
        RegistroCRUD registroCRUD = new RegistroCRUD();
        Registro registro = extraerRegistroForm(request, action);
        try {
            registroCRUD.registrarRegistro(registro);
            response.sendRedirect("/Bitacora/RegistroController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            try {
                response.sendRedirect("/Bitacora/error/error.jsp");
            } catch (IOException ex1) {
                System.out.println(ex1);
            }
        }
    }

    private Registro extraerRegistroForm(HttpServletRequest request, String action) {
        Registro registro = new Registro();
        if (action.equals("actualizar")) {
            registro.setId_registro(Integer.valueOf(request.getParameter("id_registro")));
        }
        registro.setId_sala(Integer.valueOf(request.getParameter("id_sala").trim()));
        registro.setNum_equipo(Integer.valueOf(request.getParameter("num_equipo").trim()));
        registro.setMatricula(request.getParameter("matricula").trim());
        registro.setFecha(Date.valueOf(request.getParameter("fecha").trim()));
        registro.setHora_entrada(Time.valueOf(request.getParameter("hora_entrada").trim()));
        registro.setHora_salida(Time.valueOf(request.getParameter("hora_salida").trim()));
        registro.setObservaciones(request.getParameter("num_equipo").trim());

        return registro;
    }

    private void modificarRegistro(HttpServletRequest request, HttpServletResponse response) {
        RegistroCRUD registroCRUD = new RegistroCRUD();
        int id_registro = Integer.valueOf(request.getParameter("id_registro").trim());
        try {
            Registro registro = (Registro) registroCRUD.buscarRegistro(id_registro);
            request.setAttribute("registro", registro);
            inyectarAtributos(request, "Modificar registro", "registro/actualizarRegistro.jsp");
            RequestDispatcher view = request.getRequestDispatcher("TEMPLATE/layoutTemplate.jsp");
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error al mostrar la vista: registro/actualizaRegistro.jsp");
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar registro");
            System.out.println(ex);
            listarRegistros(request, response);
        }
    }

    private void actualizarRegistro(HttpServletRequest request, HttpServletResponse response, String action) {
        Registro registro = extraerRegistroForm(request, action);
        RegistroCRUD registroCRUD = new RegistroCRUD();
        try {
            registroCRUD.actualizarRegistro(registro);
            response.sendRedirect("/Bitacora/RegistroController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarRegistros(request, response);
        }
    }

    private void eliminarRegistro(HttpServletRequest request, HttpServletResponse response) {
        RegistroCRUD registroCRUD = new RegistroCRUD();
        int id_registro = Integer.valueOf(request.getParameter("id_registro").trim());
        try {
            registroCRUD.eliminarRegistro(id_registro);
            response.sendRedirect("/Bitacora/RegistroController?action=listar");//evita acciones repetidas
        } catch (Exception ex) {
            System.out.println(ex);
            listarRegistros(request, response);
        }
    }
}
