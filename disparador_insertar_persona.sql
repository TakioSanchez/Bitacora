USE bitacora;

-- DROP PROCEDURE IF EXISTS insertar_persona;
-- DELIMITER //
--  CREATE PROCEDURE insertar_persona (IN _id_persona VARCHAR(15), IN _nombre_persona VARCHAR(60), IN _apellidos_persona VARCHAR(60), _rol VARCHAR(15))
-- BEGIN
--    IF NOT EXISTS (SELECT id_persona FROM persona where id_persona = _id_persona) THEN 
-- 		INSERT INTO persona VALUES (_id_persona, _nombre_persona, _apellidos_persona, _rol);
--    END IF;
-- END; //
-- DELIMITER ;

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


-- SELECT insertar_persona ('2012060174','Takio','Sánchez Ramos','Administrador');
-- SELECT insertar_persona ('201206169', 'Leonardo', 'Marcos Santiago', 'Administrado');
-- SELECT insertar_persona ('2012060175','Juan','X X','Administrador');
-- SELECT eliminar_persona ('2012060175');