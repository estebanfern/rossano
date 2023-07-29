--Rol
SELECT * FROM public.rol ORDER BY code ASC;
INSERT INTO public.rol (code, nombre) VALUES ('ROLE_ADMIN', 'Administrador');
INSERT INTO public.rol (code, nombre) VALUES ('ROLE_VENDEDOR', 'Vendedor');
INSERT INTO public.rol (code, nombre) VALUES ('ROLE_USER', 'Usuario');

--Permisos
SELECT * FROM public.permiso ORDER BY code ASC;
INSERT INTO public.permiso (code, nombre) VALUES ('crearProductos', 'Crear Productos');
INSERT INTO public.permiso (code, nombre) VALUES ('editarProductos', 'Editar Productos');
INSERT INTO public.permiso (code, nombre) VALUES ('eliminarProductos', 'Eliminar Productos');
INSERT INTO public.permiso (code, nombre) VALUES ('hacerPedidos', 'Hacer Pedidos');
INSERT INTO public.permiso (code, nombre) VALUES ('consultarClientes', 'Consultar Clientes');
INSERT INTO public.permiso (code, nombre) VALUES ('registrarClientes', 'Registrar Clientes');
INSERT INTO public.permiso (code, nombre) VALUES ('editarClientes', 'Editar Clientes');
INSERT INTO public.permiso (code, nombre) VALUES ('eliminarClientes', 'Editar Clientes');
INSERT INTO public.permiso (code, nombre) VALUES ('pedidosGlobales', 'Consultar todos los pedidos');
INSERT INTO public.permiso (code, nombre) VALUES ('facturar', 'Facturar');
INSERT INTO public.permiso (code, nombre) VALUES ('verUsuarios', 'Ver Usuarios');
INSERT INTO public.permiso (code, nombre) VALUES ('crudUsuarios', 'CRUD Usuarios');
INSERT INTO public.permiso (code, nombre) VALUES ('verCategorias', 'Ver Categorias');
INSERT INTO public.permiso (code, nombre) VALUES ('crudCategorias', 'CRUD Categorias');

--Permisos a Rol
SELECT * FROM public.permiso_rol ORDER BY id ASC;
--ADMIN
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('crearProductos', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('editarProductos', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('eliminarProductos', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('hacerPedidos', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('consultarClientes', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('registrarClientes', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('editarClientes', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('eliminarClientes', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('pedidosGlobales', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('facturar', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('verUsuarios', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('crudUsuarios', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('verCategorias', 'ROLE_ADMIN');
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('crudCategorias', 'ROLE_ADMIN');
--VENDEDOR
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('consultarClientes', 'ROLE_VENDEDOR');
--USER
INSERT INTO public.permiso_rol (permiso_code, rol_code) VALUES ('hacerPedidos', 'ROLE_USER');

--Usuario
SELECT * FROM public.user_info ORDER BY id ASC;
INSERT INTO public.user_info
	(email, name, password, rol, enabled)
VALUES ('estebangfernandeza@gmail.com', 'Esteban Fernández',
		'$2a$10$wNVn.YvhcJ7Afm72MR7LGua5RDSvDvDzHSmuTdnTj0T.0Q9x00Obq',
        'ROLE_ADMIN', true);
