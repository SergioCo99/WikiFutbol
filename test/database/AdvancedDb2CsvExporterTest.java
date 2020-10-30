package database;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AdvancedDb2CsvExporterTest {

	static AdvancedDb2CsvExporter exporter = new AdvancedDb2CsvExporter();

	@Before
	public void setUp() throws Exception {
		exporter = new AdvancedDb2CsvExporter();
	}

	@Test
	public void testExport() {
		String table = "pais";
		try {
			AdvancedDb2CsvExporter.export(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetFileName() {
		String table = "ciudad";

		String newFileName = AdvancedDb2CsvExporter.getFileName(table);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String dateTimeInfo = dateFormat.format(new Date());

		String expected = table + "_" + dateTimeInfo + ".csv";

		assertEquals(expected, newFileName);
	}

	@Test
	public void testWriteHeaderLine() {
		ResultSet resultset;

	}

}
