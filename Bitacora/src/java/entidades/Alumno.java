package entidades;

/**
 *
 * @author sanchez
 */
public class Alumno {
    private String matricula;
    private String matricula_anterior;
    private String nombre_alumno;
    private String apellidos_alumno;
    private String carrera;
    private String semestre;
    private String grupo;
    
    public Alumno(){}
    
    public Alumno(String matricula, String matricula_anteriror, String nombre_alumno, String apellidos_alumno, String carrera, String semestre, String grupo){
        this.matricula = matricula;
        this.matricula_anterior = matricula_anteriror;
        this.nombre_alumno = nombre_alumno;
        this.apellidos_alumno = apellidos_alumno;
        this.carrera = carrera;
        this.semestre = semestre;
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula_anterior() {
        return matricula_anterior;
    }

    public void setMatricula_anterior(String matricula_anterior) {
        this.matricula_anterior = matricula_anterior;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellidos_alumno() {
        return apellidos_alumno;
    }

    public void setApellidos_alumno(String apellidos_alumno) {
        this.apellidos_alumno = apellidos_alumno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
}
