package database;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AdvancedDb2CsvExporterTest {

	static AdvancedDb2CsvExporter exporter = new AdvancedDb2CsvExporter();

	String table = "jugador";

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	String dateTimeInfo = dateFormat.format(new Date());

	@Before
	public void setUp() throws Exception {
		exporter = new AdvancedDb2CsvExporter();
	}

	@Test
	public void testExport() {
		try {
			AdvancedDb2CsvExporter.export(table);

			String fileName = table + "_ExportFromDataBase_" + dateTimeInfo + ".csv";
			File file = new File(System.getProperty("user.home") + "/Downloads/" + fileName);
			assertTrue(file.exists());

			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void classesExport() {
		try {
			AdvancedDb2CsvExporter.classesExport(table);

			String fileName = table + "_ExportFromClasses_" + dateTimeInfo + ".csv";
			File file = new File(System.getProperty("user.home") + "/Downloads/" + fileName);
			assertTrue(file.exists());

			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
