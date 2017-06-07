package entidades;

/**
 *
 * @author sanchez
 */
public class Alumno {
    public String matricula;
    public String carrera;
    public String semestre;
    public String grupo;
    
    public Alumno(){}
    
    public Alumno(String matricula, String carrera, String semestre, String grupo){
        this.matricula = matricula;
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
