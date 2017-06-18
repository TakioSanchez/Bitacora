package entidades;

/**
 *
 * @author sanchez
 */
public class Sala {

    private int id_sala;
    private String nombre_sala;
    private int num_maquinas;
    private String id_encargado;
    private String nombre_persona;
    private String apellidos_persona;

    public Sala() {
    }

    public Sala(int id_sala, String nombre_sala, int num_maquinas, String id_encargado, String nombre_persona, String apellidos_persona) {
        this.id_sala = id_sala;
        this.nombre_sala = nombre_sala;
        this.num_maquinas = num_maquinas;
        this.id_encargado = id_encargado;
        this.nombre_persona = nombre_persona;
        this.apellidos_persona = apellidos_persona;
    }

    public Sala(int id_sala, String nombre_sala, int num_maquinas, String id_encargado) {
        this.id_sala = id_sala;
        this.nombre_sala = nombre_sala;
        this.num_maquinas = num_maquinas;
        this.id_encargado = id_encargado;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
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

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellidos_persona() {
        return apellidos_persona;
    }

    public void setApellidos_persona(String apellidos_persona) {
        this.apellidos_persona = apellidos_persona;
    }
}
