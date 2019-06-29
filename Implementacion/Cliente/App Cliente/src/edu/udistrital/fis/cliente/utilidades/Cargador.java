package edu.udistrital.fis.cliente.utilidades;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Cargador {
	URLClassLoader cargador;
	HashMap registro=new HashMap();
	URL urls[];
	public Cargador(String dir,ClassLoader padre) {
		cargarURL(dir,padre);
	}
	private void cargarURL(String dir,ClassLoader padre) {
			File archivos[] = new File(dir).listFiles();
			urls = new URL[archivos.length];
			for (int i=0;i<archivos.length;i++) {
				try {
					JarFile archivo = new JarFile(archivos[i]);
					Manifest m = archivo.getManifest();
					archivo.close();
					Attributes atributos = m.getAttributes("COMPONENTE");
					String nc = atributos.getValue("nombre");
					System.out.println(nc);
					String cl = atributos.getValue("clase");
					System.out.println(cl);
					//Lo minimo que tiene un elemento
					urls[i] = archivos[i].toURI().toURL();
					registro.put(nc,cl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cargador = new URLClassLoader(urls,padre);
	}
	public Class cargarUnaClaseDesdeSuDirectorio(String nombre) {		
		try {
			String comp = (String)registro.get(nombre);
			return comp!=null?cargador.loadClass(comp):null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
