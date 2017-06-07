package dao;

import entidades.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanchez
 */
public class PersonaCRUD extends Conexion {

    public int registrarPersona(Persona persona) throws Exception {
        String Consulta = "SELECT insertar_persona (?,?,?,?);";
        int resultado = -999;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(Consulta);
            st = cargarPersona(st, persona);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultado = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return resultado;
    }

    public PreparedStatement cargarPersona(PreparedStatement st, Persona persona) throws SQLException {
        st.setString(1, persona.getId_persona());
        st.setString(2, persona.getNombre_persona());
        st.setString(3, persona.getApellidos_persona());
        st.setString(4, persona.getRol());
        return st;
    }

    public <T> List listarPersona() throws Exception {
        List<Persona> personas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM persona")) {
                personas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Persona persona = (Persona) extraerPersona(rs);
                        personas.add(persona);
                    }
                }
            } catch (Exception e) {
                personas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return personas;
    }

    public Persona extraerPersona(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setId_persona(rs.getString("id_persona"));
        persona.setNombre_persona(rs.getString("nombre_persona"));
        persona.setApellidos_persona(rs.getString("apellidos_persona"));
        persona.setRol(rs.getString("rol"));
        return persona;
    }

    public Persona buscarPersona(String id_persona) throws Exception {
        Persona persona = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM persona WHERE id_persona = ?")) {//uso de vista??
            st.setString(1, id_persona);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    persona = extraerPersona(rs);
                }
            }
        }
        return persona;
    }

    public void actualizarPersona(Persona persona) throws Exception {
        System.out.println(persona.getId_persona_anterior());
        System.out.println(persona.getId_persona());
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE persona SET id_persona = ?, nombre_persona = ?, apellidos_persona = ?, rol = ? WHERE id_persona = ?");
            st.setString(1, persona.getId_persona());
            st.setString(2, persona.getNombre_persona());
            st.setString(3, persona.getApellidos_persona());
            st.setString(4, persona.getRol());
            st.setString(5, persona.getId_persona_anterior());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarPersona(String id_persona) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM persona WHERE id_persona = ?");
            st.setString(1, id_persona);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public Object buscarPersona(Object objeto) throws Exception {
        Persona personaM = (Persona) objeto;
        Persona persona = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM persona WHERE id_persona = ? ")) {
            st.setString(1, personaM.getId_persona());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    persona = (Persona) extraerPersona(rs);
                }
            }
        }
        return persona;
    }

}
