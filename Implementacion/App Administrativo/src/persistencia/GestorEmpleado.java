package persistencia;

import java.sql.PreparedStatement;
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

}
