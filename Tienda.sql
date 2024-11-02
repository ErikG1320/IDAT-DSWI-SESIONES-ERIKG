-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS Tienda_Micas;
USE Tienda_Micas;
-- Creación de la tabla Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre_cliente VARCHAR(100) NOT NULL,
    email_cliente VARCHAR(100),
    telefono_cliente VARCHAR(15),
    direccion_cliente VARCHAR(255),
    fecha_registro DATE
);

-- Creación de la tabla Empleados
CREATE TABLE IF NOT EXISTS Empleados (
    id_empleado INT PRIMARY KEY AUTO_INCREMENT,
    nombre_empleado VARCHAR(100) NOT NULL,
    email_empleado VARCHAR(100),
    telefono_empleado VARCHAR(15),
    puesto VARCHAR(50),
    fecha_contratacion DATE
);

-- Creación de la tabla Productos
CREATE TABLE IF NOT EXISTS Productos (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_producto VARCHAR(100) NOT NULL,
    descripcion_producto TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    fecha_agregado DATE
);

-- Creación de la tabla Ventas
CREATE TABLE IF NOT EXISTS Ventas (
    id_venta INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT,
    id_empleado INT,
    fecha_venta DATE,
    total DECIMAL(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado)
);

-- Creación de la tabla Detalle_Venta
CREATE TABLE IF NOT EXISTS Detalle_Venta (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_venta INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2),
    FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Procedimiento para agregar un cliente
DELIMITER //
CREATE PROCEDURE agregar_cliente (
    IN nombre VARCHAR(100),
    IN email VARCHAR(100),
    IN telefono VARCHAR(15),
    IN direccion VARCHAR(255),
    IN fecha_reg DATE
)
BEGIN
    INSERT INTO Clientes (nombre_cliente, email_cliente, telefono_cliente, direccion_cliente, fecha_registro)
    VALUES (nombre, email, telefono, direccion, fecha_reg);
END //
DELIMITER ;

-- Procedimiento para agregar un producto
DELIMITER //
CREATE PROCEDURE agregar_producto (
    IN nombre VARCHAR(100),
    IN descripcion TEXT,
    IN precio DECIMAL(10, 2),
    IN stock INT,
    IN fecha_agreg DATE
)
BEGIN
    INSERT INTO Productos (nombre_producto, descripcion_producto, precio, stock, fecha_agregado)
    VALUES (nombre, descripcion, precio, stock, fecha_agreg);
END //
DELIMITER ;

-- Procedimiento para registrar una venta
DELIMITER //
CREATE PROCEDURE registrar_venta (
    IN cliente_id INT,
    IN empleado_id INT,
    IN fecha DATE,
    IN total DECIMAL(10, 2)
)
BEGIN
    INSERT INTO Ventas (id_cliente, id_empleado, fecha_venta, total)
    VALUES (cliente_id, empleado_id, fecha, total);
END //
DELIMITER ;

-- Procedimiento para agregar un detalle de venta
DELIMITER //
CREATE PROCEDURE agregar_detalle_venta (
    IN venta_id INT,
    IN producto_id INT,
    IN cantidad INT,
    IN precio DECIMAL(10, 2)
)
BEGIN
    INSERT INTO Detalle_Venta (id_venta, id_producto, cantidad, precio_unitario)
    VALUES (venta_id, producto_id, cantidad, precio);
END //
DELIMITER ;

-- Procedimiento para eliminar un cliente y sus ventas
DELIMITER //
CREATE PROCEDURE eliminar_cliente (
    IN id_cliente INT
)
BEGIN
    -- Se eliminarán automáticamente las ventas y los detalles de venta debido a ON DELETE CASCADE
    DELETE FROM Clientes WHERE id_cliente = id_cliente;
END //
DELIMITER ;

-- Procedimiento para actualizar un cliente
DELIMITER //
CREATE PROCEDURE actualizar_cliente (
    IN id_cliente INT,
    IN nombre VARCHAR(100),
    IN email VARCHAR(100),
    IN telefono VARCHAR(15),
    IN direccion VARCHAR(255)
)
BEGIN
    UPDATE Clientes
    SET nombre_cliente = nombre,
        email_cliente = email,
        telefono_cliente = telefono,
        direccion_cliente = direccion
    WHERE id_cliente = id_cliente;
END //
DELIMITER ;
