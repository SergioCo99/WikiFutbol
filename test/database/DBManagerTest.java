package database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Entrenador;
import clases.Estadio;
import clases.Entrenador.Mentalidad;

public class DBManagerTest {

	// Métodos Entrenador

	Entrenador e = new Entrenador(1, "Gaizka Garitano", "1975-07-09", "Athletic Club", "Bilbao", "4-3-3",
			Mentalidad.Equilibrada);

	@Test
	public void nombreEntrenador() throws DBManagerException {
		assertEquals(e.getNombre(), DBManager.nombreEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void fechaNacimiento() throws DBManagerException {
		assertEquals(e.getFechaNac(), DBManager.fechaNacimiento("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void clubEntrenador() throws DBManagerException {
		assertEquals(e.getClub(), DBManager.clubEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void ciudadEntrenador() throws DBManagerException {
		assertEquals(e.getCiudad(), DBManager.ciudadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void formacionEntrenador() throws DBManagerException {
		assertEquals(e.getFormacion(), DBManager.formacionEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void mentalidadEntrenador() throws DBManagerException {
		assertEquals("Equilibrada", DBManager.mentalidadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	// Fin Métodos Entrenador
	
	// Métodos Estadio

	Estadio es = new Estadio(1, "San Mames", 53289, 2013, "Bilbao");
	 
	@Test
	public void nombreEstadio() throws DBManagerException {
		assertEquals(es.getNombre(), DBManager.nombreEstadio("San Mames", "wikifutbolschema"));
	}
	
	@Test
	public void aforoEstadio() throws DBManagerException {
		assertEquals(es.getAforo(), DBManager.aforoEstadio("San Mames", "wikifutbolschema"));
	}
	
	@Test
	public void anyoEstadio() throws DBManagerException {
		assertEquals(es.getAnyoCreacion(), DBManager.anyoEstadio("San Mames", "wikifutbolschema"));
	}
	
	@Test
	public void ciudadEstadio() throws DBManagerException {
		assertEquals(es.getCiudad(), DBManager.ciudadEstadio("San Mames", "wikifutbolschema"));
	}
	
	//Fin Métodos Estadio
}
