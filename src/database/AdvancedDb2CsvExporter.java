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
import java.util.Date;
import java.util.Properties;

public class AdvancedDb2CsvExporter {

	private static BufferedWriter fileWriter;

	public static void export(String table) throws Exception {

		Properties prop = utils.PropertiesMetodos.loadPropertiesFile();
		String CONTROLADOR = prop.getProperty("DB.CONTROLADOR");
		String URL = prop.getProperty("DB.URL");
		String USUARIO = prop.getProperty("DB.USUARIO");
		String CONTRASENA = prop.getProperty("DB.CONTRASENA");

		Class.forName(CONTROLADOR); // esto para que sirve???
		Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

		String csvFileName = getFileName(table.concat("_Export"));

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

					if (valueObject != null)
						valueString = valueObject.toString();

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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String getFileName(String baseName) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String dateTimeInfo = dateFormat.format(new Date());
		return baseName.concat(String.format("_%s.csv", dateTimeInfo));
	}

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

	private static String escapeDoubleQuotes(String value) {
		return value.replaceAll("\"", "\"\"");
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws Exception {
		// asi en otra clase
		database.AdvancedDb2CsvExporter.export("jugador");
	}
}