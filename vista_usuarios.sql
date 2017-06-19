USE bitacora;

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
FROM usuario AS u, persona AS p WHERE u.nombre_usuario = p.id_persona;

-- INSERT INTO usuario (nombre_usuario,contrasenia,metodo) VALUES ('2012060169',sha2(concat('UnS15','hola','51SnU'),224),'sha2');

