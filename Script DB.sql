/* Create Tables */

CREATE TABLE Cine
(
	idCine SERIAL primary key,
	nombreCine text NOT NULL,
	direccion text NOT NULL
)
;

create table tipoEmpleado(
	idTipoEmpleado serial primary key,
	descripcion text not null
);

CREATE TABLE Empleado
(
	idEmpleado integer primary key,
	nombreEmpleado text NOT NULL,
	apellidoEmpleado text NOT NULL,
	identificacionEmpleado integer unique NOT NULL,
	idCine integer,
	estado integer not null,
	idTipoEmpleado integer not null
)
;
ALTER TABLE Empleado 
ADD CONSTRAINT FK_Empleado_Cine FOREIGN KEY (idCine) REFERENCES Cine (idCine) ON DELETE No Action ON UPDATE No Action,
add constraint fk_empleado_tipoEmpleado foreign key (idTipoEmpleado) references tipoEmpleado (idTipoEmpleado) on delete no action on update no action;

create table TipoUsuario(
	idTipoUsuario SERIAL primary key,
	descripcion text NOT NULL
);

create table Usuario(
	idUsuario text primary key,
	contrasena text NOT NULL,
	idEmpleado integer NOT NULL,
	idTipoUsuario integer NOT NULL,
	idCineEncargado integer
);
alter table Usuario
add constraint fk_usuario_empleado foreign key (idEmpleado) references Empleado (idEmpleado) on delete no action on update no action,
add constraint fk_usuario_tipoUsuario foreign key (idTipoUsuario) references TipoUsuario (idTipoUsuario) on delete no action on update no action,
add constraint fk_usuario_cine foreign key (idCineEncargado) references Cine (idCine) on delete no action on update no action;

CREATE TABLE Producto
(
	idProducto integer primary key,
	nombre text NOT NULL,
	unidadMedicion text NOT NULL
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

create table pelicula
(
	idPelicula serial primary key,
	nombrePelicula text not null,
	sinopsis text not null,
	fechaEstreno date not null,
	duracion interval not null,
	nombreDirector not null,
	img bytea not null
);

CREATE TABLE Sala
(
	idSala SERIAL primary key,
	consecutivo integer NOT NULL,
	idCine integer NOT NULL
)
;

ALTER TABLE Sala ADD CONSTRAINT FK_Sala_Cine
	FOREIGN KEY (idCine) REFERENCES Cine (idCine) ON DELETE No Action ON UPDATE No Action
;

create table Asiento(
	fila text not null,
	columna text not null,
	idSala integer not null
);
alter table asiento
add constraint pk_asiento primary key (fila,columna,idSala),
add constraint fk_asiento_sala foreign key (idSala) references Sala (idSala) on delete no action on update no action;

create table Funcion
(
	idFuncion serial primary key,
	idPelicula integer not null,
	fecha date not null,
	hora interval not null,
	idSala integer not null,
	estado integer not null
);
alter table funcion
add constraint pk_funcion_pelicula foreign key (idPelicula) references Pelicula (idPelicula) on delete no action on update no action,
add constraint pk_funcion_sala foreign key (idSala) references Sala (idSala) on delete no action on update no action;

create table Espacio
(
	idFuncion integer,
	fila char(1),
	columna char(1),
	idSala integer
);
alter table Espacio
add constraint pk_espacio primary key (idFuncion,fila,columna,idSala),
add constraint fk_espacio_funcion foreign key (idFuncion) references Funcion (idFuncion) on delete no action on update no action,
add constraint fk_espacio_asiento foreign key (fila,columna,idSala) references Asiento (fila,columna,idSala) on delete no action on update no action;


CREATE TABLE Combo
(
	idCombo serial primary key,
	descripcion text NOT NULL,
	precio decimal NOT NULL,
	estado integer NOT NULL,
	img bytea not null
)
;

CREATE TABLE TipoSuscripcion
(
	ipSuscripcion SERIAL primary key,
	nombre text NOT NULL,
	descripcion text NOT NULL
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

CREATE TABLE Boleta
(
	idBoleta integer primary key,
	fila char(1)	 NOT NULL,
	columna char(1)	 NOT NULL,
	idFuncion integer NOT NULL,
	idCompra integer NOT NULL,
	idHistoricoBoleta integer NOT NULL,
	idSala integer not null
)
;

alter table boleta
add constraint fk_boleta_espacio foreign key (idFuncion,idSala,fila,columna) references espacio (idFuncion,idSala,fila,columna) ON DELETE No Action ON UPDATE No Action,
add constraint fk_boleta_compra foreign key (idCompra) references compra (idCompra) ON DELETE No Action ON UPDATE No Action,
add constraint fk_boleta_historico foreign key (idHistoricoBoleta) references HistoricoBoleta (idHistoricoBoleta) ON DELETE No Action ON UPDATE No Action;


-- VIEWS

create view consolidadoFunciones as
select 
pelicula.nombrePelicula,
pelicula.duracion duracionPelicula,
funcion.fecha fechaFuncion,
funcion.hora horaFuncion,
(funcion.hora+pelicula.duracion) horaTerminacion,
cine.nombreCine,
sala.consecutivo,
funcion.estado
from
pelicula, sala, cine,funcion
where
pelicula.idPelicula = funcion.idPelicula and
sala.idSala = funcion.idSala and
sala.idCine = cine.idCine
order by cine.idCine,funcion.fecha;

create view HoraTerminacionFunciones as
SELECT funcion.idfuncion,
sala.consecutivo,
pelicula.nombrepelicula,
pelicula.duracion,
funcion.hora AS horainicio,
pelicula.duracion + funcion.hora AS horaterminacion
FROM pelicula,
funcion,
sala
WHERE funcion.idsala = sala.idsala AND funcion.idpelicula = pelicula.idpelicula AND sala.idcine = 1;


create view cantidad_funciones_por_fecha_por_cine as
select nombrePelicula, fechafuncion,idCine, count(*) from consolidadoFunciones
group by nombrePelicula, fechafuncion,idCine order by fechafuncion;



--Hora de terminacion de las funciones
create view hora_terminacion_funciones as
select
funcion.idFuncion,
sala.consecutivo,
pelicula.nombrePelicula,
pelicula.duracion,
funcion.hora horaInicio,
(pelicula.duracion + funcion.hora) horaTerminacion 
from
pelicula, funcion, sala
where
funcion.idSala = sala.idSala and
funcion.idPelicula = pelicula.idPelicula;





