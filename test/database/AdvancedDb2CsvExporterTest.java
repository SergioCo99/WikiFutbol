package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de AdvancedDb2CsvExporter
 * @author sergi
 *
 */
public class AdvancedDb2CsvExporterTest {

	static AdvancedDb2CsvExporter exporter = new AdvancedDb2CsvExporter();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
	String dateTimeInfo = dateFormat.format(new Date());

	/**
	 * Crea una nuevo AdvancedDb2CsvExporter
	 *
	 * @throws Exception En caso de error
	 */
	@Before
	public void setUp() throws Exception {
		exporter = new AdvancedDb2CsvExporter();
	}

	/**
	 * Prueba el metodo preparado para exportar tablas
	 *
	 */
	@Test
	public void testExport() {
		try {
			for (String table : database.DBManager.verTablas()) {
				try {
					AdvancedDb2CsvExporter.export(table);

					String fileName = table + "_ExportFromDataBase_" + dateTimeInfo + ".csv";
					File file = new File(System.getProperty("user.home") + "/Downloads/" + fileName);
					assertTrue(file.exists());

					Scanner myReader = new Scanner(file);
					int lineas = 0;
					while (myReader.hasNextLine()) {
						lineas++;
						myReader.nextLine();
					}
					myReader.close();

					assertEquals(database.DBManager.numeroDeFilasEnUnaTabla(table) + 1, lineas);

					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (DBManagerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prueba el metodo que sirve para exportar en un archivo csv tablas de la base
	 * de datos
	 *
	 */
	@Test
	public void classesExport() {
		try {
			for (String table : database.DBManager.verTablas()) {
				try {
					AdvancedDb2CsvExporter.classesExport(table);

					String fileName = table + "_ExportFromClasses_" + dateTimeInfo + ".csv";
					File file = new File(System.getProperty("user.home") + "/Downloads/" + fileName);
					assertTrue(file.exists());

					Scanner myReader = new Scanner(file);
					int lineas = 0;
					while (myReader.hasNextLine()) {
						lineas++;
						myReader.nextLine();
					}
					myReader.close();

					if (table.equals("ciudad")) {
						assertEquals(database.DBManager.getCiudades().size(), lineas);
					} else if (table.equals("club")) {
						assertEquals(database.DBManager.getClubes().size(), lineas);
					} else if (table.equals("entrenador")) {
						assertEquals(database.DBManager.getEntrenadores().size(), lineas);
					} else if (table.equals("estadio")) {
						assertEquals(database.DBManager.getEstadios().size(), lineas);
					} else if (table.equals("feedback")) {
						assertEquals(database.DBManager.getFeedbacks().size(), lineas);
					} else if (table.equals("jugador")) {
						assertEquals(database.DBManager.getJugadores().size(), lineas);
					} else if (table.equals("pais")) {
						assertEquals(database.DBManager.getPaises().size(), lineas);
					} else if (table.equals("teamoftheyear")) {
						assertEquals(database.DBManager.getTeamOfTheYear().size(), lineas);
					} else if (table.equals("teamoftheyear_view")) {
						assertEquals(database.DBManager.getTeamOfTheYear_view().size(), lineas);
					} else if (table.equals("usuario")) {
						assertEquals(database.DBManager.getUsuarios().size(), lineas);
					} else if (table.equals("usuariovotacion")) {
						assertEquals(database.DBManager.getUsuarioVotaciones().size(), lineas);
					}

					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (DBManagerException e) {
			e.printStackTrace();
		}
	}

}
