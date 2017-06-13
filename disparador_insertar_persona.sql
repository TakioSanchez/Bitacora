USE bitacora;

-- Función para saber si la persona fue ingresada correctamente o no 
DROP FUNCTION IF EXISTS insertar_persona;
DELIMITER //
CREATE FUNCTION insertar_persona (_id_persona VARCHAR(15), _nombre_persona VARCHAR(60), _apellidos_persona VARCHAR(60), _rol VARCHAR(15))
RETURNS INT(1)
BEGIN
    
    -- Consultamos si la persona
    IF EXISTS (SELECT id_persona FROM persona WHERE id_persona = _id_persona) THEN
		RETURN 0;
    ELSE
		IF (_rol = 'Administrador' OR _rol = 'Alumno') THEN
			INSERT INTO persona VALUES (_id_persona, _nombre_persona, _apellidos_persona, _rol);
            RETURN 1;
		ELSE
			RETURN -1;
        END IF;
    END IF;
    
END; //
DELIMITER ;

DROP FUNCTION IF EXISTS eliminar_persona;
DELIMITER //
CREATE FUNCTION eliminar_persona (_id_persona VARCHAR(15))
RETURNS INT(1)
BEGIN
	IF EXISTS (SELECT id_persona FROM persona WHERE id_persona = _id_persona) THEN
		DELETE FROM persona WHERE id_persona = _id_persona;
        RETURN 1;
    ELSE
		RETURN 0;
    END IF;
END ; //
DELIMITER ;

-- Función para saber si la persona fue ingresada correctamente o no 
DROP FUNCTION IF EXISTS insertar_alumno;
DELIMITER //
CREATE FUNCTION insertar_alumno (_matricula VARCHAR(15), _carrera VARCHAR(60), _semestre VARCHAR(15), _grupo VARCHAR(10))
RETURNS INT(1)
BEGIN
    
    -- Primero consultamos si existe la persona
	IF EXISTS(SELECT matricula FROM alumno WHERE matricula = _matricula) THEN
		RETURN 0;
	ELSE
		INSERT INTO alumno VALUES (_matricula, _carrera, _semestre, _grupo);
		RETURN 1;
	END IF;
    
END; //
DELIMITER ;

-- Función para saber si la persona fue ingresada correctamente o no 
DROP FUNCTION IF EXISTS actualizar_alumno;
DELIMITER //
CREATE FUNCTION actualizar_alumno (_id_persona VARCHAR(15), _id_persona_anterior VARCHAR(15), _nombre_persona VARCHAR(60), _apellidos_persona VARCHAR(60), _carrera VARCHAR(60), _semestre VARCHAR(15), _grupo VARCHAR(10))
RETURNS INT(1)
BEGIN    
    -- Primero consultamos si existe la persona
    IF NOT EXISTS(SELECT id_persona FROM persona WHERE id_persona = _id_persona_anterior) THEN
		RETURN 0;
	ELSE
		IF NOT EXISTS(SELECT matricula FROM alumno WHERE matricula = _id_persona_anterior) THEN
			RETURN -1;
        ELSE
			UPDATE persona SET id_persona = _id_persona, nombre_persona = _nombre_persona, apellidos_persona = _apellidos_persona WHERE id_persona = _id_persona_anterior;
            UPDATE alumno SET carrera = _carrera, semestre = _semestre, grupo = _grupo WHERE matricula = _id_persona;
            RETURN 1;
        END IF;
    END IF;
END; //
DELIMITER ;

DROP VIEW IF EXISTS VISTA_ALUMNOS;
CREATE VIEW VISTA_ALUMNOS AS 
SELECT 
	id_persona,
    nombre_persona,
    apellidos_persona,
    carrera,
    semestre,
    grupo
FROM persona, alumno
WHERE id_persona = matricula
ORDER BY id_persona;

select * from alumno;
select * from persona;
Select id_persona,nombre_persona,apellidos_persona,carrera,semestre,grupo FROM persona,alumno WHERE id_persona = matricula;
select * FROM VISTA_ALUMNOS;
UPDATE persona SET id_persona = '201303030', nombre_persona = 'Amairanii', apellidos_persona = 'Castillo Gutierrez' WHERE id_persona = '2013030320' ;
SELECT actualizar_alumno ('2013030350','2013030320','Amairani','Castillo Gutierrez','enfermera','8','803-B');