package database;

import org.junit.Before;
import org.junit.Test;

public class AdvancedDb2CsvExporterTest {

	static AdvancedDb2CsvExporter exporter = new AdvancedDb2CsvExporter();

	@Before
	public void setUp() throws Exception {
		exporter = new AdvancedDb2CsvExporter();
	}

	@Test // SIN HACER
	public void testExport() {
		String table = "pais";
		try {
			AdvancedDb2CsvExporter.export(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
