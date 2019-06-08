package edu.udistrital.fis.basicos.presentacion;

import javax.swing.JFrame;
/**
 * Clase abstracta que ser� extendida en todos las clases que sean Frames
 * @author Andr�s Arias
 *
 */
public abstract class AbstractFrame extends JFrame {
	protected String identificador;
	/**
	 * M�todo abstracto en el que se especificar� el atributo 'identificador'. El identificador es el nombre que tendr� el frame en el menu principal
	 */
	protected abstract void setIdentificador();
	public String getIdentificador() {
		return this.identificador;
	}
}
