package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import clases.Ciudad;
import clases.Club;
import clases.Entrenador;
import clases.Entrenador.Mentalidad;
import clases.Estadio;
import clases.Feedback;
import clases.Feedback.Recomendacion;
import clases.Jugador;
import clases.Jugador.PieFav;
import clases.Jugador.Posicion;
import clases.Pais;
import clases.TeamOfTheYear;
import clases.TeamOfTheYear_view;
import clases.Usuario;
import clases.UsuarioVotacion;

public class db {

	static Connection conn;
	static Statement stmt = null;

	public static void connect() throws DBManagerException {
		try {
			Properties prop = utils.PropertiesMetodos.loadPropertiesFile();
			String CONTROLADOR = prop.getProperty("DB.CONTROLADOR");
			String URL = prop.getProperty("DB.URL");
			String USUARIO = prop.getProperty("DB.USUARIO");
			String CONTRASENA = prop.getProperty("DB.CONTRASENA");

			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			System.out.println("CONEXION");
		} catch (Exception e) {
			throw new DBManagerException("Error connect DBManager", e);
		}
	}

	public static void disconnect() throws DBManagerException {
		try {
			conn.close();
			System.out.println("DESCONEXION");
		} catch (SQLException e) {
			throw new DBManagerException("Error disconnect DBManager", e);
		}
	}

	public static ArrayList<String> verTablas() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "use wikifutbolschema;";
			stmt.executeQuery(sql1);
			String sql2 = "show tables;";
			ResultSet rs = stmt.executeQuery(sql2);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("Tables_in_wikifutbolschema"));
			}
			// rs.close();
			// stmt.close();
			// disconnect();
			System.out.println(arr);
			return arr;
		} catch (SQLException e) {
			throw new DBManagerException("Error verTablas DBManager", e);
		}
	}

	public static ArrayList<String> verColumnas(String tabla) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "select column_name from INFORMATION_SCHEMA.columns where table_name = '" + tabla
					+ "' order by ordinal_position";
			ResultSet rs = stmt.executeQuery(sql1);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("column_name"));
			}
			// rs.close();
			// stmt.close();
			// disconnect();
			System.out.println(arr);
			return arr;
		} catch (SQLException e) {
			throw new DBManagerException("Error verColumnas DBManager", e);
		}
	}

	public static Object[][] data(String tabla) throws DBManagerException {
		// Object oo[][] = null;
		ArrayList<String> as = new ArrayList<String>();

		try {
			connect();
			stmt = conn.createStatement();

			// sacar datos
			String sql1 = "select * from " + tabla + ";";
			System.out.println(sql1);
			ResultSet rs1 = stmt.executeQuery(sql1);

			// numero de columnas
			int ncolumns = verColumnas(tabla).size();
			System.out.println("ncolumns: " + ncolumns);

			// numero de filas
			String sql2 = "select count(*) from " + tabla + ";";
			System.out.println(sql2);
			ResultSet rs2 = stmt.executeQuery(sql2);
			rs2.next();
			int nrows = rs2.getInt("count(*)");
			System.out.println("nrows: " + nrows);

			// meter datos en array 2D
			while (rs1.next()) {
				for (int i = 1; i <= ncolumns; i++) {
					as.add(rs1.getObject(i).toString());
				}
			}
			System.out.println(as);

			int z = 0;
			String[][] ss = null;
			for (int j = 0; j < nrows; j++) {
				for (int i = 0; i < ncolumns; i++) {
					System.out.println("fila: " + j + ", columna: " + i + " -> " + as.get(z));
					// arraylist to 2d
					ss[j][i] = as.get(z).toString();
					//
					z++;
					
				}
			}

			System.out.println(as.get(1));

			// rs1.close();
			stmt.close();
			disconnect();
			return ss;
		} catch (SQLException e) {
			throw new DBManagerException("Error data DBManager", e);
		}
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		data("pais");
	}
}
