package database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Entrenador;
import clases.Estadio;
import clases.Entrenador.Mentalidad;

public class DBManagerTest {

	// Métodos Entrenador

	Entrenador e = new Entrenador(1, "Gaizka Garitano", "2000-10-10", "Athletic Club", "Bilbao", "Escuela Albacete",
			Mentalidad.Defensiva);

	@Test
	public void nombreEntrenador() throws DBManagerException {
		assertEquals(e.getNombre(), DBManager.nombreEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void fechaNacimiento() throws DBManagerException {
		assertEquals("2000-10-10", DBManager.fechaNacimiento("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void clubEntrenador() throws DBManagerException {
		assertEquals("Athletic Club", DBManager.clubEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void ciudadEntrenador() throws DBManagerException {
		assertEquals("Bilbao", DBManager.ciudadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void formacionEntrenador() throws DBManagerException {
		assertEquals("Escuela Albacete", DBManager.formacionEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void mentalidadEntrenador() throws DBManagerException {
		assertEquals("Defensiva", DBManager.mentalidadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	// Fin Métodos Entrenador

}
