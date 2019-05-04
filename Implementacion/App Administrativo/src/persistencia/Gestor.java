package persistencia;

import java.sql.SQLException;

public abstract class Gestor {
	
	protected GestorDB gestor;
	
	protected Gestor() throws SQLException {
		gestor = GestorDB.getInstance();
	}
}