TRUNCATE roles;

INSERT INTO roles(id, nombre, descripcion) VALUES (1, "ROLE_USER", "Usuarios normales que registran");
INSERT INTO roles(id, nombre, descripcion) VALUES (2, "ROLE_ADMIN", "Usuarios administradores");