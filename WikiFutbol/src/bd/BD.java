package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Equipo;

public class BD {
	public static Connection initBD(String BD) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD +"?userTimezone=true&serverTimezone=UTC", "root", "admin");
			return con;
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void cerrarBD( Connection con, Statement st ) {
		try {
			if (st!=null) st.close();
			if (con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Equipo> getEquipos(String BD) throws SQLException {
		ArrayList<Equipo> array = new ArrayList<Equipo>();
		Connection con = initBD(BD);
		Statement stmt = con.createStatement();
		ResultSet RS = stmt.executeQuery("SELECT * FROM equipo");
		while(RS.next()) {
			Equipo e = new Equipo(RS.getInt(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getInt(5), RS.getString(6), RS.getString(7));
			array.add(e);
		}
		return array;
	}
}
