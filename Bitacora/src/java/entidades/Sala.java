package entidades;

/**
 *
 * @author sanchez
 */
public class Sala {
    private String id_sala;
    private String id_sala_anterior;//para actualizar
    private String nombre_sala;
    private int    num_maquinas;
    private String id_encargado;
    
    public Sala() {
    }

    public Sala(String id_sala, String id_sala_anterior, String nombre_sala,int num_maquinas, String id_encargado) {
        this.id_sala = id_sala;
        this.id_sala_anterior = id_sala_anterior;
        this.nombre_sala = nombre_sala;
        this.num_maquinas = num_maquinas;
        this.id_encargado = id_encargado;
    }

    public String getId_sala() {
        return id_sala;
    }

    public void setId_sala(String id_sala) {
        this.id_sala = id_sala;
    }

    public String getId_sala_anterior() {
        return id_sala_anterior;
    }

    public void setId_sala_anterior(String id_sala_anterior) {
        this.id_sala_anterior = id_sala_anterior;
    }

    public String getNombre_sala() {
        return nombre_sala;
    }

    public void setNombre_sala(String nombre_sala) {
        this.nombre_sala = nombre_sala;
    }

    public int getNum_maquinas() {
        return num_maquinas;
    }

    public void setNum_maquinas(int num_maquinas) {
        this.num_maquinas = num_maquinas;
    }

    public String getId_encargado() {
        return id_encargado;
    }

    public void setId_encargado(String id_encargado) {
        this.id_encargado = id_encargado;
    }
}