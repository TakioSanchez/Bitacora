USE bitacora;
SELECT * FROM alumno;
-- vista usuarios
DROP VIEW IF EXISTS vista_usuarios;
CREATE VIEW vista_usuarios AS
SELECT
	nombre_usuario,
    contrasenia,
    metodo,
    nombre_persona,
    apellidos_persona,
    rol
FROM usuario AS u, persona AS p, alumno AS a 
WHERE u.nombre_usuario = p.id_persona AND p.id_persona = a.matricula;

-- INSERT INTO usuario (nombre_usuario,contrasenia,metodo) VALUES ('2012060169',sha2(concat('UnS15','hola','51SnU'),224),'sha2');
-- INSERT INTO usuario (nombre_usuario,contrasenia,metodo) VALUES ('dasodkljasidoaj',sha2(concat('UnS15','hola','51SnU'),224),'sha2');
SELECT * FROM persona;
SELECT * FROM alumno;
SELECT * FROM usuario;