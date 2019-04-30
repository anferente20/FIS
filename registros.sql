--Tabla Cine
insert into Cine (nombreCine,direccion) values ('Cine+ Andino','Cra. 11 #82-71'),
												('Cine+ Cedritos','Cl. 151 #16-56'),
												('Cine+ Unicentro','Ak. 15 #124-30'),
												('Cine+ Centro Mayor','Calle 38 A #Sur No. 34D-51'),
												('Cine+ Metrópolis','Ak 68 #75a-50'),
												('Cine+ Galerías','Cl. 53b #25-21'),
												('Cine+ Gran Estación','Av. Calle 26 #62-47'),
												('Cine+ Las Américas','Cra. 71d #6-94'),
												('Cine+ Portal 80','Cl. 80 #100 - 52'),
												('Cine+ Titán Plaza','Av. Boyacá #80-94');
												
select * from cine;

--Tabla Empleado

insert into Empleado (idEmpleado,nombreEmpleado,apellidoEmpleado,identificacionEmpleado,idCine) values (1615,'Juan', 'Uribe Alvarez',1442401402,1),
																										(1222,'Daniel Alexander', 'Mejia Uribe',1906959327,1),
																										(1167,'Daniel Fernando', 'Mora Arias',1416500504,2),
																										(1556,'Daniel', 'Castillo Rodriguez',1036929289,2),
																										(1948,'Juan', 'Uribe Arias',1919678821,10),
																										(1496,'Jhon Raul', 'Castillo',1596474852,10),
																										(1566,'Jhon Camilo', 'Castillo',1977542729,3),
																										(1391,'Felipe Leonardo', 'Uribe Uribe',1793786382,4),
																										(1152,'Daniel Raul', 'Mora Arias',1635963930,4),
																										(1263,'Felipe Emilio', 'Uribe Uribe',1410552682,5),
																										(1178,'Andres Leonardo', 'Mora Uribe',1924210545,6),
																										(1529,'Hernando Fernando', 'Castillo',1968997572,6),
																										(1294,'Jhon Raul', 'Arias Alvarez',1014224864,7),
																										(1163,'Juan', 'Arias Rodriguez',1773961104,8),
																										(1342,'Felipe Fernando', 'Castrillon',1331035069,8),
																										(1118,'Jhon Leonardo', 'Mora Alvarez',1006621433,9),
																										(1171,'Natalia Estefania', 'Mora Arias',1202475853,10),
																										(1023,'Marcela Diana', 'Castillo Uribe',1813158091,9),
																										(1364,'Estefania Natalia', 'Castrillon Hernandez',1402201065,9),
																										(1034,'Laura Natalia', 'Castillo',1668899243,8),
																										(1293,'Natalia Natalia', 'Mejia',1148838561,7),
																										(1476,'Laura Natalia', 'Perez Rodriguez',1494578546,7),
																										(1865,'Laura Laura', 'Mora Arias',1730020155,6),
																										(1677,'Laura Angye', 'Mejia Rodriguez',1681668766,5),
																										(1861,'Estefania Isabel', 'Arias',1500770254,5),
																										(1964,'Laura Isabel', 'Mejia',1504228837,4),
																										(1857,'Diana Karen', 'Mora Arias',1248313443,3),
																										(1594,'Karen Isabel', 'Arias Hernandez',1191033563,3),
																										(1629,'Natalia Marcela', 'Uribe Arias',1984699631,2),
																										(1560,'Karen Diana', 'Castillo Hernandez',1683186027,1);
																										
select * from Empleado;

--Tabla tipoUsuario

insert into TipoUsuario (descripcion) values ('Administrador'),('Super-Usuario');

--Tabla usuario

insert into Usuario (idUsuario,contrasena,idEmpleado,idTipoUsuario,idCineEncargado) values ('1442401402','admin1',1615,1,1),
																							('1984699631','admin2',1629,1,2),
																							('1248313443','admin3',1857,1,3),
																							('1504228837','admin4',1964,1,4),
																							('1410552682','admin5',1263,1,5),
																							('1968997572','admin6',1529,1,6),
																							('1014224864','admin7',1294,1,7),
																							('1668899243','admin8',1034,1,8),
																							('1006621433','admin9',1118,1,9),
																							('1202475853','admin10',1171,1,10),
																							('1919678821','superadmin',1948,2,10);
																							


											
