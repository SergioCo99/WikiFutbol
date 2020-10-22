package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntrenadorTest {

	private Entrenador e;
	
	@Before
	public void setUp() {
		e = new Entrenador(1, "Gaizka Garitano", 1970, 1, 1, "Escuela Albacete", Mentalidad.Defensiva);
	}

	@Test
	public void testId() {
		e.setId(1);
		assertEquals(1, e.getId());
	}
	
	@Test
	public void testNombre() {
		
		e.setNombre("Gaizka Garitano");
		assertEquals("Gaizka Garitano", e.getNombre());
	}
	
	@Test
	public void testAnyoNacimiento() {
		
		e.setAnyoNacimiento(1970);
		assertEquals(1970, e.getAnyoNacimiento());
	}
	
	@Test
	public void testClub() {
		
		e.setClub(1);
		assertEquals(1 , e.getClub());
	}
	
	@Test
	public void testCiudad() {
		
		e.setCiudad(1);
		assertEquals(1, e.getCiudad());
	}
	
	@Test
	public void testFormacion() {
		
		e.setFormacion("Escuela Albacete");
		assertEquals("Escuela Albacete", e.getFormacion());
	}
	
	@Test
	public void testMentalidad() {
		
		e.setMentalidad(Mentalidad.Defensiva);
		assertEquals(Mentalidad.Defensiva, e.getMentalidad());
	}
	
	@Test
	public void metodoToString() {
		
		e.setId(1);
		e.setNombre("Gaizka Garitano");
		e.setAnyoNacimiento(1980);
		e.setClub(1);
		e.setCiudad(1);
		e.setFormacion("Escuela Albacete");
		e.setMentalidad(Mentalidad.Defensiva);
		
		System.out.println(e.toString());
		
		assertEquals(
				"Entrenador [formacion=Escuela Albacete, mentalidad=Defensiva, id=1, nombre=Gaizka Garitano, anyoNacimiento=1980, club=1, ciudad=1, getId()=1, getNombre()=Gaizka Garitano, getAnyoNacimiento()=1980, getClub()=1, getCiudad()=1, toString()=EmpleadoDeClub [id=1, nombre=Gaizka Garitano, anyoNacimiento=1980, club=1, ciudad=1]]",
				e.toString());
	}
}
