package edu.udistrital.fis.basicos.presentacion;

import javax.swing.JFrame;
/**
 * Clase abstracta que será extendida en todos las clases que sean Frames
 * @author Andrés Arias
 *
 */
public abstract class AbstractFrame extends JFrame {
	protected String identificador;
	/**
	 * Método abstracto en el que se especificará el atributo 'identificador'. El identificador es el nombre que tendrá el frame en el menu principal
	 */
	protected abstract void setIdentificador();
	public String getIdentificador() {
		return this.identificador;
	}
}
