/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.0 		*/
/*  Created On : 27-abr.-2019 18:10:56 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */


/* Create Tables */

CREATE TABLE Cine
(
	idCine SERIAL primary key,
	nombreCine text NOT NULL,
	direccion text NOT NULL
)
;



CREATE TABLE Empleado
(
	idEmpleado integer primary key,
	nombreEmpleado text NOT NULL,
	apellidoEmpleado text NOT NULL,
	identificacionEmpleado integer unique NOT NULL,
	idCine integer NOT NULL
)
;


ALTER TABLE Empleado 
ADD CONSTRAINT FK_Empleado_Cine FOREIGN KEY (idCine) REFERENCES Cine (idCine) ON DELETE No Action ON UPDATE No Action;

create table TipoUsuario(
	idTipoUsuario SERIAL primary key,
	descripcion text NOT NULL
);

create table Usuario(
	idUsuario text primary key,
	contrasena text NOT NULL,
	idEmpleado integer NOT NULL,
	idTipoUsuario SERIAL NOT NULL,
	idCineEncargado SERIAL
);

alter table Usuario
add constraint fk_usuario_empleado foreign key (idEmpleado) references Empleado (idEmpleado) on delete no action on update no action,
add constraint fk_usuario_tipoUsuario foreign key (idTipoUsuario) references TipoUsuario (idTipoUsuario) on delete no action on update no action,
add constraint fk_usuario_cine foreign key (idCineEncargado) references Cine (idCine) on delete no action on update no action;






CREATE TABLE TipoSuscripcion
(
	ipSuscripcion SERIAL primary key,
	nombre text NOT NULL,
	descripcion text NOT NULL
)
;

CREATE TABLE Sala
(
	idSala SERIAL primary key,
	consecutivo integer NOT NULL DEFAULT nextval(('sala_consecutivo_seq'::text)::regclass),
	totalAsientos integer NOT NULL,
	totalFilas integer NOT NULL,
	idCine integer NOT NULL
)
;

ALTER TABLE Sala ADD CONSTRAINT FK_Sala_Cine
	FOREIGN KEY (idCine) REFERENCES Cine (idCine) ON DELETE No Action ON UPDATE No Action
;



CREATE TABLE Reserva
(
	idReserva integer primary key,
	idFuncion integer NOT NULL,
	estado boolean NOT NULL,
	idCliente integer NOT NULL,
	idCompra integer NOT NULL,
	idHistoricoReserva integer NOT NULL
)
;

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_Cliente
	FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_Compra
	FOREIGN KEY (idCompra) REFERENCES Compra (idCompra) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_Funcion
	FOREIGN KEY (idFuncion) REFERENCES Funcion (idFuncion) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_historicoValorReserva
	FOREIGN KEY (idHistoricoReserva) REFERENCES HistoricoValorReserva (idHistoricoReserva) ON DELETE No Action ON UPDATE No Action
;

CREATE TABLE Producto
(
	idProducto integer primary key,
	nombre text NOT NULL,
	unidadMedicion text NOT NULL
)
;

CREATE TABLE Pelicula
(
	idPelicula integer primary key,
	nombrePelicula text NOT NULL,
	sinopsis text NOT NULL,
	fechaEstreno date NOT NULL
)
;

CREATE TABLE Inventario
(
	cantidad integer NOT NULL,
	idCine integer NOT NULL,
	idProducto integer NOT NULL
)
;


ALTER TABLE Inventario ADD CONSTRAINT PK_Inventario
	PRIMARY KEY (idCine,idProducto);


ALTER TABLE Inventario ADD CONSTRAINT FK_Inventario_Cine
	FOREIGN KEY (idCine) REFERENCES Cine (idCine) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Inventario ADD CONSTRAINT FK_Inventario_Producto
	FOREIGN KEY (idProducto) REFERENCES Producto (idProducto) ON DELETE No Action ON UPDATE No Action
;

CREATE TABLE HistoricoValorReserva
(
	idHistoricoReserva SERIAL primary key,
	valor decimal NOT NULL,
	fecha date NOT NULL
)
;

CREATE TABLE HistoricoPreciosBoleta
(
	idHistoricoBoleta SERIAL primary key,
	precio decimal NOT NULL,
	fecha date NOT NULL
)
;

CREATE TABLE Funcion
(
	idFuncion integer primary key,
	hora time NOT NULL,
	fecha date NOT NULL,
	idSala integer NOT NULL,
	idPelicula integer NOT NULL
)
;


ALTER TABLE Funcion ADD CONSTRAINT FK_Funcion_Pelicula
	FOREIGN KEY (idPelicula) REFERENCES Pelicula (idPelicula) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Funcion ADD CONSTRAINT FK_Funcion_Sala
	FOREIGN KEY (idSala) REFERENCES Sala (idSala) ON DELETE No Action ON UPDATE No Action
;




CREATE TABLE Compra
(
	idCompra integer primary key,
	fecha date NOT NULL,
	hora time NOT NULL,
	idCliente integer NOT NULL,
	total decimal NOT NULL
)
;

ALTER TABLE Compra ADD CONSTRAINT FK_Compra_Cliente
	FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente) ON DELETE No Action ON UPDATE No Action
;


CREATE TABLE ComboCompra
(
	cantidad integer NOT NULL,
	subtotal decimal NOT NULL,
	idCompra integer NOT NULL,
	idCombo integer NOT NULL
)
;

ALTER TABLE ComboCompra ADD CONSTRAINT PK_ComboCompra
	PRIMARY KEY (idCompra,idCombo)
;

ALTER TABLE ComboCompra ADD CONSTRAINT FK_ComboCompra_Combo
	FOREIGN KEY (idCombo) REFERENCES Combo (idCombo) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE ComboCompra ADD CONSTRAINT FK_ComboCompra_Compra
	FOREIGN KEY (idCompra) REFERENCES Compra (idCompra) ON DELETE No Action ON UPDATE No Action;




CREATE TABLE Combo
(
	idCombo integer primary key,
	descripcion text NOT NULL,
	precio decimal NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE Cliente
(
	idCliente integer primary key,
	nombreCliente text NOT NULL,
	apellidoCliente text NOT NULL,
	identificacionCliente integer NOT NULL,
	tipoSuscripcion integer NOT NULL,
	ipSuscripcion integer NOT NULL
)
;

ALTER TABLE Cliente ADD CONSTRAINT FK_Cliente_tipoSuscripcion
	FOREIGN KEY (ipSuscripcion) REFERENCES TipoSuscripcion (ipSuscripcion) ON DELETE No Action ON UPDATE No Action
;



CREATE TABLE Boleta
(
	idBoleta integer primary key,
	fila char(1)	 NOT NULL,
	columna char(1)	 NOT NULL,
	idFuncion integer NOT NULL,
	idCompra integer NOT NULL,
	idHistoricoBoleta integer NOT NULL
)
;

ALTER TABLE Boleta ADD CONSTRAINT FK_Boleta_Compra
	FOREIGN KEY (idCompra) REFERENCES Compra (idCompra) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Boleta ADD CONSTRAINT FK_Boleta_Funcion
	FOREIGN KEY (idFuncion) REFERENCES Funcion (idFuncion) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Boleta ADD CONSTRAINT FK_Boleta_HistoricoPreciosBoleta
	FOREIGN KEY (idHistoricoBoleta) REFERENCES HistoricoPreciosBoleta (idHistoricoBoleta) ON DELETE No Action ON UPDATE No Action
;














