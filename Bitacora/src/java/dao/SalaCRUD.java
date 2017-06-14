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
    
    public <T> List listarSala() throws Exception {
        List<Sala> salas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM sala")) {
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
        sala.setId_sala(rs.getString("id_sala"));
        sala.setNombre_sala(rs.getString("nombre_sala"));
        sala.setNum_maquinas(rs.getInt("num_maquinas"));
        sala.setId_encargado(rs.getString("id_encargado"));
        return sala;
    }
    
}
