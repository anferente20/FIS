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
		String consulta = "insert into Empleado (idEmpleado,nombreEmpleado,apellidoEmpleado,identificacionEmpleado,idCine,estado,idTipoEmpleado) values"
				+ "(?,?,?,?,?,1,2);";
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
				+ "? || '%' and estado = 1;";
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
				+ "CAST(identificacionEmpleado as text) like ? || '%'  and estado = 1;";
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
				+ "Cine.nombreCine as cine from Empleado, Cine where  Empleado.idEmpleado = ? and Empleado.idCine = Cine.idCine  and estado = 1";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, id);
		return sentencia.executeQuery();	
	}
	
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		String consulta = "update Empleado set nombreEmpleado = ?, apellidoEmpleado = ?, identificacionEmpleado = ? , idCine = ? where idEmpleado = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, empleado.getNombresEmpleado());
		sentencia.setString(2, empleado.getApellidosEmpleados());
		sentencia.setInt(3, empleado.getIdentificacionEmpl());
		sentencia.setInt(4, empleado.getIdCine());
		sentencia.setInt(5, empleado.getIdEmpleado());
		sentencia.execute();	
	}
	
	public ResultSet consultarEmpleadoByCine(int idCine) throws SQLException {
		String consulta = "select empleado.idEmpleado as id , empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, empleado.identificacionEmpleado as identificacion,\r\n" + 
				"				tipoEmpleado.descripcion as tipoEmpleado from empleado, tipoEmpleado" + 
				"				where empleado.idTipoEmpleado = tipoEmpleado.idTipoEmpleado and empleado.idCine = ? and estado = 1 order by empleado.idTipoEmpleado asc;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCine);
		return sentencia.executeQuery();	
	}
	//sugiere empleados por nombre, apellidos o nombre del cine
	public ResultSet sugerirEmpleados(String parametro) throws SQLException {
		String consulta = "select empleado.idEmpleado as id, empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, identificacionEmpleado\r\n" + 
				"				as identificacion, cine.nombreCine as nombreCine from empleado, cine where empleado.estado = 1 and\r\n" + 
				"				empleado.idTipoEmpleado = 2 and empleado.idCine = cine.idCine and (lower(empleado.nombreEmpleado) like ? || '%'  or\r\n" + 
				"				lower(empleado.apellidoEmpleado) like ? || '%' or  lower(cine.nombreCine) like 'cine+ ' || ? || '%');";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, parametro);
		sentencia.setString(2, parametro);
		sentencia.setString(3, parametro);
		return sentencia.executeQuery();
	}
	//sugiere empleados por id o identificación
	public ResultSet sugerirEmpleados(int id) throws SQLException {
		String consulta = "select empleado.idEmpleado as id, empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, identificacionEmpleado\r\n" + 
				"				as identificacion, cine.nombreCine as nombreCine from empleado, cine where empleado.estado = 1 and \r\n" + 
				"				empleado.idTipoEmpleado = 2 and empleado.idCine = cine.idCine and CAST(empleado.idEmpleado as text) like ? || '%'  or\r\n" + 
				"				CAST(empleado.identificacionEmpleado as text) like ? || '%';";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, String.valueOf(id));
		sentencia.setString(2, String.valueOf(id));
		return sentencia.executeQuery();
	}
	
	public void darBajaEmpleado(int idEmpleado) throws SQLException {
		String consulta = "update empleado set estado = 0 where idEmpleado = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idEmpleado);
		sentencia.execute();
	}
	
	public ResultSet consultarAdmins() throws SQLException {
		String consulta = "select empleado.idEmpleado || ' - ' || empleado.nombreEmpleado || ' ' || empleado.apellidoEmpleado as adminInfo, cine.nombreCine as cine \r\n" + 
				"from (usuario inner join empleado on empleado.idEmpleado = usuario.idEmpleado) inner join cine on cine.idCine = usuario.idCineEncargado order by cine.idCine;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
	

}
