package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorCine extends Gestor{
	
	public GestorCine() throws SQLException {
		super();
	}
	
	public ResultSet consultarCines() throws SQLException {
		String consulta = "select nombreCine from Cine order by idCine asc;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}

}
