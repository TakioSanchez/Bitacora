/*
	
    BASE DE DATOS para ELECTRONICLOGS
		
    Autor: Irving Ulises Hernández Miguel
		   Leonardo Marcos Santiago
           Takio Sánchez Ramos
           Edward Armando de la Luz Ortiz
    
    Creación: 6 de abril de 2017
    Ultima modificacición 9 de mayo de 2017
    
*/
DROP DATABASE IF EXISTS bitacora;
CREATE DATABASE IF NOT EXISTS bitacora;

USE bitacora;

CREATE TABLE personas(
	id_persona VARCHAR(15) NOT NULL,
    nombre_persona VARCHAR(60) NOT NULL,
    apellidos_persona VARCHAR(60) NOT NULL,
    rol VARCHAR(15) NOT NULL,
    PRIMARY KEY (id_persona)
)ENGINE=INNODB 
 DEFAULT CHARSET=utf8 
 COLLATE=utf8_general_ci;
 
CREATE TABLE alumnos(
	matricula VARCHAR(15) NOT NULL,
    carrera VARCHAR(60) NOT NULL,
    semestre VARCHAR(15) NOT NULL,
    grupo VARCHAR(10) NOT NULL,
    PRIMARY KEY (matricula)
)ENGINE=INNODB 
 DEFAULT CHARSET=utf8 
 COLLATE=utf8_general_ci;
 
CREATE TABLE usuarios(
	usuario 		VARCHAR(15) NOT NULL,
    contrasenia			varchar(50) NOT NULL,
	metodo 				ENUM('sha1') DEFAULT 'sha1' NOT NULL, -- metodo para encriptar contraseña
    PRIMARY KEY(usuario)
)ENGINE=INNODB 
 DEFAULT CHARSET=utf8 
 COLLATE=utf8_general_ci;

CREATE TABLE salas(
	id_sala INT unsigned NOT NULL auto_increment,
    nombre_sala VARCHAR(30) NOT NULL,
    num_maquinas INT(3) NOT NULL,
    id_encargado VARCHAR(15) NOT NULL,
    PRIMARY KEY (id_sala)
)ENGINE=INNODB 
 DEFAULT CHARSET=utf8 
 COLLATE=utf8_general_ci 
 AUTO_INCREMENT=1;


CREATE TABLE registros(
	id_registro INT unsigned NOT NULL auto_increment,
    id_sala INT unsigned NOT NULL,
    num_equipo INT(3) NOT NULL,
    matricula VARCHAR(15) NOT NULL,
    fecha DATE NOT NULL,
    hora_entrada TIME NOT NULL,
    hora_salida TIME,
    observaciones VARCHAR(255),
    PRIMARY KEY (id_registro)
)ENGINE=INNODB 
 DEFAULT CHARSET=utf8 
 COLLATE=utf8_general_ci 
 AUTO_INCREMENT=1;

/*
	Modificaciones para las
	Restricciones de llaves foráneas
*/
ALTER TABLE alumnos
	ADD CONSTRAINT fkalumnos_personas
    FOREIGN KEY (matricula)
    REFERENCES personas(id_persona) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE registros 
	ADD CONSTRAINT fkregistros_salas
	FOREIGN KEY (id_sala)
    REFERENCES salas(id_sala) ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE registros
	ADD CONSTRAINT fkregistros_alumnos
	FOREIGN KEY (matricula)	
    REFERENCES alumnos(matricula) ON DELETE CASCADE ON UPDATE CASCADE;

/*
ALTER TABLE Alumnos
  ADD CONSTRAINT fkAlumn1
  FOREIGN KEY (idE)
    REFERENCES Escuelas(idE)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
 */   
/*        
INSERT INTO Escuelas(nombre,direccion,telefono,fax,celular,correo,observaciones)
	VALUES ('Benito Juárez','2 de Abril #203','57-215-234',
			'0998 455879','951-234-4567','benij@hotmail.com',
            'Esta escuela se ubica en una zona muy lejana por los coatlanes');
            
INSERT INTO Cursos(fechaIni,fechaFin,notas,idE)
	VALUES ('2012-01-01','2012-02-02','Curso dado por Irving',1);
 
 
INSERT INTO Alumnos(nombre,apeP,apeM,asistencias,constancia,
					grado,grupo,observaciones,idE,idC)
	VALUES ('Juan','Ramírez','Ramírez',4,TRUE,
			3,'B','Muy tranquilo el muchacho',2,3);
 */           