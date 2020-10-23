package interfaces;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.JList;

import org.junit.Before;
import org.junit.Test;

import clases.Club;
import database.DBManager;
import database.DBManagerException;

public class IListaTest {

	private IListaEquipos interfaz;
	private DBManager database = new DBManager();
	private ArrayList<Club> array = new ArrayList<Club>();
	private JList lista = new JList<String>();

	@Before
	public void setUp() {
		try {
			array = database.getClubes();
			System.out.println("BD cargada");
		} catch (DBManagerException e) {
			System.out.println("BD no cargada");
		}
	}

	@Test
	public void actualizarLista() {
		IListaEquipos.cargarLista(lista, array);

		assertEquals(array.size(), lista.getModel().getSize());

	}

}
