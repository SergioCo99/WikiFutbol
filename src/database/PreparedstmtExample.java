package database;

public class PreparedstmtExample {

	public static void main(String[] args) throws DBManagerException {
		System.out.println(DBManager.numeroDeFilasEnUnaTabla("pais"));
	}

}
// NO VAN: (recordar revisar ".close()")

// actualizarVotos, igual contarVotosPorJugador y contarJugadores

// getMasVotados puede que no vaya, depende indirectamente de actualizarVotos
// toft igual no va, depende de getMasVotados, que depende indirectamente de actualizarVotos

// data

// cambiarDatos

// cambiarDatosDesdeJTable

// numeroDeFilasEnUnaTabla