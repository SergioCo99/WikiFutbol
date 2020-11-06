package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

public class AdvancedDb2CsvExporter {

	private static BufferedWriter fileWriter;

	/**
	 * Este método sirve para exportar tablas de la BD
	 * 
	 * @param table Nombre de la tabla que queremos exportar
	 * @throws Exception En caso de error
	 */
	public static void export(String table) throws Exception {

		Properties prop = utils.PropertiesMetodos.loadPropertiesFile();
		String CONTROLADOR = prop.getProperty("DB.CONTROLADOR");
		String URL = prop.getProperty("DB.URL");
		String USUARIO = prop.getProperty("DB.USUARIO");
		String CONTRASENA = prop.getProperty("DB.CONTRASENA");

		Class.forName(CONTROLADOR);
		Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

		String csvFileName = getFileName(table.concat("_ExportFromDataBase"));

		try {
			String sql = "SELECT * FROM ".concat(table);

			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sql);

			String home = System.getProperty("user.home");
			fileWriter = new BufferedWriter(new FileWriter(home + "/Downloads/" + csvFileName));

			int columnCount = writeHeaderLine(result);

			while (result.next()) {
				String line = "";

				// from first column
				for (int i = 1; i <= columnCount; i++) {
					Object valueObject = result.getObject(i);
					String valueString = "";

					if (valueObject != null) {
						valueString = valueObject.toString();
					}

					if (valueObject instanceof String) {
						valueString = "\"" + escapeDoubleQuotes(valueString) + "\"";
					}

					line = line.concat(valueString);

					if (i != columnCount) {
						line = line.concat(",");
					}
				}

				fileWriter.newLine();
				fileWriter.write(line);
			}

			statement.close();
			fileWriter.close();

		} catch (SQLException e) {
			e.printStackTrace();
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
		}

	}

	/**
	 * Este método devuelve el nombre del archivo en "String" con la fecha y hora
	 * 
	 * @param baseName
	 * @return nombre del archivo en String
	 */
	private static String getFileName(String baseName) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String dateTimeInfo = dateFormat.format(new Date());
		return baseName.concat(String.format("_%s.csv", dateTimeInfo));
	}

	/**
	 * Este método sirve para escibir los nombres de las columnas en el .csv
	 * 
	 * @return Numero de columnas, para usar en otros métodos
	 * @throws SQLException En caso de error
	 * @throws IOException En caso de error
	 */
	private static int writeHeaderLine(ResultSet result) throws SQLException, IOException {
		// write header line containing column names
		ResultSetMetaData metaData = result.getMetaData();
		int numberOfColumns = metaData.getColumnCount();
		String headerLine = "";

		// from first column
		for (int i = 1; i <= numberOfColumns; i++) {
			String columnName = metaData.getColumnName(i);
			headerLine = headerLine.concat(columnName).concat(",");
		}

		fileWriter.write(headerLine.substring(0, headerLine.length() - 1));

		return numberOfColumns;
	}

	/**
	 * Sirve para sustituir el contenido de un String por el de otro String
	 * 
	 * @param value Contenido que queremos añadir
	 * @return Devuelve el cambio de contenido
	 */
	private static String escapeDoubleQuotes(String value) {
		return value.replaceAll("\"", "\"\"");
	}

	/**
	 * Este método sirve para exportar en un archivo csv tablas de la base de datos
	 * 
	 * @param table Nombre de la tabla a exportar
	 * @throws DBManagerException En caso de error
	 */
	public static void classesExport(String table) throws DBManagerException {
		String csvFileName = getFileName(table.concat("_ExportFromClasses"));
		ArrayList<?> arr = null;

		if (table.equals("ciudad")) {
			arr = database.DBManager.getCiudades();
		} else if (table.equals("club")) {
			arr = database.DBManager.getClubes();
		} else if (table.equals("entrenador")) {
			arr = database.DBManager.getEntrenadores();
		} else if (table.equals("estadio")) {
			arr = database.DBManager.getEstadios();
		} else if (table.equals("feedback")) {
			arr = database.DBManager.getFeedbacks();
		} else if (table.equals("jugador")) {
			arr = database.DBManager.getJugadores();
		} else if (table.equals("pais")) {
			arr = database.DBManager.getPaises();
		} else if (table.equals("teamoftheyear")) {
			arr = database.DBManager.getTeamOfTheYear();
		} else if (table.equals("teamoftheyear_view")) {
			arr = database.DBManager.getTeamOfTheYear_view();
		} else if (table.equals("usuario")) {
			arr = database.DBManager.getUsuarios();
		} else if (table.equals("usuariovotacion")) {
			arr = database.DBManager.getUsuarioVotaciones();
		}

		try {
			String home = System.getProperty("user.home");
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(home + "/Downloads/" + csvFileName));
			for (Object str : arr) {
				writer.write(str + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			e.printStackTrace();
		}
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws Exception {
		// asi en otra clase
		database.AdvancedDb2CsvExporter.export("pais");
	}
}