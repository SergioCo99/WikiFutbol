package interfaces;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import org.junit.Before;
import org.junit.Test;

import clases.Club;
import database.DBManager;
import database.DBManagerException;

/**
 * Test de las IListas
 *
 * @author sergi
 *
 */
public class IListaTest {

	private List<Club> arrayClub = new ArrayList<Club>();
	private List<String> arrayJugador = new ArrayList<String>();
	private JList<String> listaClub = new JList<String>();
	private JList<String> listaJugador = new JList<String>();

	/**
	 * Realiza un "get" para recibir todos los clubes de la base de datos
	 */
	@Before
	public void setUp() {
		try {
			arrayClub = DBManager.getClubes();
			System.out.println("BD cargada");
		} catch (DBManagerException e) {
			System.out.println("BD no cargada");
		}
	}

	/**
	 * Realiza un "get" para recibir todos los jugadores de un equipo concreto En
	 * este caso "Athletic Club"
	 */
	@Before
	public void setUp1() {

		try {
			String nombreEquipo = "Athletic Club";
			arrayJugador = DBManager.getJugadoresPorEquipo(nombreEquipo);
			System.out.println("BD cargada");
		} catch (DBManagerException e) {
			System.out.println("BD no cargada");
		}

	}

	/**
	 * Carga esos datos en la ILista correspondiente Verifica que el tamanyo del
	 * array y el de la lista es el mismo
	 */
	@Test
	public void actualizarLista() {
		IListaEquipos.cargarLista(listaClub, arrayClub);
		IListaJugadores.cargarLista(listaJugador, arrayJugador);

		assertEquals(arrayClub.size(), listaClub.getModel().getSize());
		assertEquals(arrayJugador.size(), listaJugador.getModel().getSize());

	}

}
