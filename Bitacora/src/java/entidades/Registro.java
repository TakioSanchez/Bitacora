package entidades;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author lmarcoss
 */
public class Registro {

    private int id_registro;
    private int id_sala;
    private int num_equipo;
    private String matricula;
    private String nombre_completo;
    private Date fecha;
    private Time hora_entrada;
    private Time hora_salida;
    private String observaciones;

    public Registro() {
    }

    public Registro(int id_registro, int id_sala, int num_equipo, String matricula, String nombre_completo, Date fecha, Time hora_entrada, Time hora_salida, String observaciones) {
        this.id_registro = id_registro;
        this.id_sala = id_sala;
        this.num_equipo = num_equipo;
        this.matricula = matricula;
        this.nombre_completo = nombre_completo;
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.observaciones = observaciones;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getNum_equipo() {
        return num_equipo;
    }

    public void setNum_equipo(int num_equipo) {
        this.num_equipo = num_equipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
