package edu.udistrital.fis.empleado.logica;

public class Empleado {
	private int idEmpleado;
	private String nombresEmpleado;
	private String apellidosEmpleados;
	private int identificacionEmpl;
	private int idCine;
	
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombresEmpleado() {
		return nombresEmpleado;
	}
	public void setNombresEmpleado(String nombresEmpleado) {
		this.nombresEmpleado = nombresEmpleado;
	}
	public String getApellidosEmpleados() {
		return apellidosEmpleados;
	}
	public void setApellidosEmpleados(String apellidosEmpleados) {
		this.apellidosEmpleados = apellidosEmpleados;
	}
	public int getIdentificacionEmpl() {
		return identificacionEmpl;
	}
	public void setIdentificacionEmpl(int identificacionEmpl) {
		this.identificacionEmpl = identificacionEmpl;
	}
	public int getIdCine() {
		return idCine;
	}
	public void setIdCine(int idCine) {
		this.idCine = idCine;
	}
}