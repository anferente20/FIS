package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Empleado;

public class GestorEmpleado extends Gestor{
	
	public GestorEmpleado() throws SQLException {
		super();
	}
	
	public void insertarEmpleado(Empleado empleado) throws SQLException {
		String consulta = "insert into Empleado (idEmpleado,nombreEmpleado,apellidoEmpleado,identificacionEmpleado,idCine) values"
				+ "(?,?,?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, empleado.getIdEmpleado());
		sentencia.setString(2, empleado.getNombresEmpleado());
		sentencia.setString(3, empleado.getApellidosEmpleados());
		sentencia.setInt(4, empleado.getIdentificacionEmpl());
		sentencia.setInt(5, empleado.getIdCine());
		sentencia.execute();
	}
	
	//Buscar a los empleados por nombre o apellido
	public ResultSet autoCompletarEmpleado(String nombreApellido) {
		ResultSet empleados = null;
		String consulta = "select idEmpleado || ' - '|| nombreEmpleado || ' ' || apellidoEmpleado from Empleado where lower(nombreEmpleado) like ? || '%' or lower(apellidoEmpleado) like "
				+ "? || '%';";
		try {
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setString(1, nombreApellido.toLowerCase());
			sentencia.setString(2, nombreApellido.toLowerCase());
			empleados = sentencia.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return empleados;
	}
	
	//Buscar a los empleados por identificación o ID
	public ResultSet autoCompletarEmpleado(int id) {
		ResultSet empleados = null;
		String consulta = "select idEmpleado || ' - '|| nombreEmpleado || ' ' || apellidoEmpleado from Empleado where CAST(idEmpleado as text) like ? || '%' or "
				+ "CAST(identificacionEmpleado as text) like ? || '%';";
		try {
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setString(1,String.valueOf(id));
			sentencia.setString(2,String.valueOf(id));
			empleados = sentencia.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return empleados;
	}
	
	public ResultSet consultarEmpleadoByID(int id) throws SQLException {
		String consulta = "select empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, empleado.identificacionEmpleado as identificacion,"
				+ "Cine.nombreCine as cine from Empleado, Cine where  Empleado.idEmpleado = ? and Empleado.idCine = Cine.idCine";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, id);
		return sentencia.executeQuery();	
	}
	
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		String consulta = "update Empleado set nombreEmpleado = ?, apellidoEmpleado = ?, identificacionEmpleado = ? where idEmpleado = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, empleado.getNombresEmpleado());
		sentencia.setString(2, empleado.getApellidosEmpleados());
		sentencia.setInt(3, empleado.getIdentificacionEmpl());
		sentencia.setInt(4, empleado.getIdEmpleado());
		sentencia.execute();	
	}

}
