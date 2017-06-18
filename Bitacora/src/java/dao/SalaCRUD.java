package dao;

import entidades.Sala;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanchez
 */
public class SalaCRUD extends Conexion{
    
    public void registrarSala(Sala sala) throws Exception {
        String Consulta = "INSERT INTO sala (id_sala,nombre_sala,num_maquinas,id_encargado) VALUES (?,?,?,?)";
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(Consulta);
            st = cargarSala(st, sala);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }
    
    public PreparedStatement cargarSala(PreparedStatement st, Sala sala) throws SQLException {
        st.setInt(1, sala.getId_sala());
        st.setString(2, sala.getNombre_sala());
        st.setInt(3, sala.getNum_maquinas());
        st.setString(4, sala.getId_encargado());
        return st;
    }
    
    public <T> List listarSala() throws Exception {
        List<Sala> salas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_SALAS")) {
                salas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Sala sala = (Sala) extraerSala(rs);
                        salas.add(sala);
                    }
                }
            } catch (Exception e) {
                salas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return salas;
    }
    
    public Sala extraerSala(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        sala.setId_sala(rs.getInt("id_sala"));
        sala.setNombre_sala(rs.getString("nombre_sala"));
        sala.setNum_maquinas(rs.getInt("num_maquinas"));
        sala.setId_encargado(rs.getString("id_encargado"));
        sala.setNombre_persona(rs.getString("nombre_persona"));
        sala.setApellidos_persona(rs.getString("apellidos_persona"));
        return sala;
    }
    
    public Sala buscarSala(int id_sala) throws Exception {
        Sala sala = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM sala WHERE id_sala = ?")) {
            st.setInt(1, id_sala);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    sala = new Sala(rs.getInt("id_sala"), rs.getString("nombre_sala"), rs.getInt("num_maquinas"), rs.getString("id_encargado"));
                }
            }
        }
        return sala;
    }

    public void actualizarSala(Sala sala, String id_encargado) throws Exception {
        int contador = 0;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE sala SET nombre_sala = ?,num_maquinas=?,id_encargado=? WHERE id_sala = ?");
            st.setString(++contador, sala.getNombre_sala());
            st.setInt(++contador, sala.getNum_maquinas());
            st.setString(++contador, id_encargado);
            st.setInt(++contador, sala.getId_sala());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }
    
    public void eliminarSala(int id_sala) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM sala WHERE id_sala = ?");
            st.setInt(1, id_sala);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }
}
