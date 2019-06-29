--Tabla Cine
insert into 
Cine (nombreCine,direccion) 
values 	
('Cine+ Andino','Cra. 11 #82-71'),
('Cine+ Cedritos','Cl. 151 #16-56'),
('Cine+ Unicentro','Ak. 15 #124-30'),
('Cine+ Centro Mayor','Calle 38 A #Sur No. 34D-51'),
('Cine+ Metrópolis','Ak 68 #75a-50'),
('Cine+ Galerías','Cl. 53b #25-21'),
('Cine+ Gran Estación','Av. Calle 26 #62-47'),
('Cine+ Las Américas','Cra. 71d #6-94'),
('Cine+ Portal 80','Cl. 80 #100 - 52'),
('Cine+ Titán Plaza','Av. Boyacá #80-94');											
												
--Tabla tipoEmpleado
insert into 
tipoEmpleado (descripcion) 
values 
('Administrador'),
('Normal');

--Tabla Empleado
insert into 
Empleado (idEmpleado,nombreEmpleado,apellidoEmpleado,identificacionEmpleado,idCine,idTipoEmpleado,estado) 
values 
(1615,'Juan', 'Uribe Alvarez',1442401402,1,1,1),
(1222,'Daniel Alexander', 'Mejia Uribe',1906959327,1,2,1),
(1167,'Daniel Fernando', 'Mora Arias',1416500504,2,2,1),
(1556,'Daniel', 'Castillo Rodriguez',1036929289,2,2,1),
(1948,'Juan', 'Uribe Arias',1919678821,null,1,1),
(1496,'Jhon Raul', 'Castillo',1596474852,10,2,1),
(1566,'Jhon Camilo', 'Castillo',1977542729,3,2,1),
(1391,'Felipe Leonardo', 'Uribe Uribe',1793786382,4,2,1),
(1152,'Daniel Raul', 'Mora Arias',1635963930,4,2,1),
(1263,'Felipe Emilio', 'Uribe Uribe',1410552682,5,1,1),
(1178,'Andres Leonardo', 'Mora Uribe',1924210545,6,2,1),
(1529,'Hernando Fernando', 'Castillo',1968997572,6,1,1),
(1294,'Jhon Raul', 'Arias Alvarez',1014224864,7,1,1),
(1163,'Juan', 'Arias Rodriguez',1773961104,8,2,1),
(1342,'Felipe Fernando', 'Castrillon',1331035069,8,2,1),
(1118,'Jhon Leonardo', 'Mora Alvarez',1006621433,9,1,1),
(1171,'Natalia Estefania', 'Mora Arias',1202475853,10,1,1),
(1023,'Marcela Diana', 'Castillo Uribe',1813158091,9,2,1),
(1364,'Estefania Natalia', 'Castrillon Hernandez',1402201065,9,2,1),
(1034,'Laura Natalia', 'Castillo',1668899243,8,1,1),
(1293,'Natalia Natalia', 'Mejia',1148838561,7,2,1),
(1476,'Laura Natalia', 'Perez Rodriguez',1494578546,7,2,1),
(1865,'Laura Laura', 'Mora Arias',1730020155,6,2,1),
(1677,'Laura Angye', 'Mejia Rodriguez',1681668766,5,2,1),
(1861,'Estefania Isabel', 'Arias',1500770254,5,2,1),
(1964,'Laura Isabel', 'Mejia',1504228837,4,1,1),
(1857,'Diana Karen', 'Mora Arias',1248313443,3,1,1),
(1594,'Karen Isabel', 'Arias Hernandez',1191033563,3,2,1),
(1629,'Natalia Marcela', 'Uribe Arias',1984699631,2,1,1),
(1560,'Karen Diana', 'Castillo Hernandez',1683186027,1,2,1);

--Tabla tipoUsuario
insert into 
TipoUsuario (descripcion) 
values 
('Administrador'),
('Super-Usuario');

--Tabla usuario
insert into 
Usuario (idUsuario,contrasena,idEmpleado,idTipoUsuario,idCineEncargado) 
values 
('1442401402','admin1',1615,1,1),
('1984699631','admin2',1629,1,2),
('1248313443','admin3',1857,1,3),
('1504228837','admin4',1964,1,4),
('1410552682','admin5',1263,1,5),
('1968997572','admin6',1529,1,6),
('1014224864','admin7',1294,1,7),
('1668899243','admin8',1034,1,8),
('1006621433','admin9',1118,1,9),
('1202475853','admin10',1171,1,10),
('1919678821','superadmin',1948,2,null);

--Tabla producto
insert into
producto (idProducto,nombre,unidadMedicion)
values
(42804,'Salsa queso chedar','Litro'),
(44075,'Maiz','Bulto'),
(42909,'Vaso gaseosa 10 onz','Unidad'),
(34936,'Vaso gaseosa 8 onz','Unidad'),
(33429,'Envase crispetas 12 onz','Unidad'),
(37715,'Envase crispetas 14 onz','Unidad'),
(44922,'Gaseosa postobón manzana','Litro'),
(29637,'Gaseosa postobón uva','Litro'),
(37810,'Gaseosa kola roman','Litro'),
(33775,'Gaseosa coca cola','Litro'),
(44408,'Gaseosa quatro','Litro'),
(42078,'Nachos','Paquete'),
(38801,'Salchicha para perro','Unidad'),
(40694,'Pitillo','Unidad'),
(41565,'Tapa de gaseosa','Unidad'),
(30308,'Soporte para perro','Unidad'),
(33942,'Chocolatina yet','Unidad'),
(28440,'Gaseosa pepsi','Litro'),
(35605,'Gaseosa sevenUp','Litro'),
(46560,'Endulzante para ice sabor fresa','Paquetes');

--Tabla HistoricosPreciosBoleta

insert into historicoPreciosBoleta (precio,fecha) values (12000,'28/06/2019');

--Tabla TipoSuscripcion

insert into tiposuscripcion (ipsuscripcion,nombre,descripcion)
values(1,'Basico','No tiene beneficios. No tiene ningún costo'),
(2,'Platino','5% de descuento en compras tiene un precio de $50.000 anuales'),
(3,'Premium','10% de descuento en compras, tiene un precio de $100.000 anuales');
