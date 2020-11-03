package utils;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Clase mejorada de JLabel para gestionar imagenes ajustadas al JLabel
 */

public class JLabelGraficoAjustado extends JLabel {

	private static final long serialVersionUID = 1L;

	protected int anchuraLogo;
	protected int alturaLogo;
	protected double radsRotacionLogo;
	protected float opacidadLogo;
	protected BufferedImage imagenLogoWiki;

	/**
	 * Crea un nuevo JLabel grafico.<br>
	 * Si no existe el fichero de imagen, se crea un rectangulo blanco con borde
	 * rojo
	 *
	 * @param nombreimagenLogoWiki Nombre fichero donde esta la imagen del objeto.
	 *                             Puede ser tambien un nombre de recurso desde el
	 *                             paquete de esta clase.
	 * @param anchura              Anchura del grafico en pixels
	 * @param altura               Altura del grafico en pixels
	 */

	public JLabelGraficoAjustado(String nombreImagenLogoWiki, int anchura, int altura) {

		setName(nombreImagenLogoWiki);
		opacidadLogo = 1.0f;
		setImagen(nombreImagenLogoWiki); // Carga del Logo
		setSize(anchura, altura);

	}

	@Override
	public void setSize(int anchura, int altura) {

		if ((anchura <= 0) && (imagenLogoWiki != null)) {
			anchura = imagenLogoWiki.getWidth();
		}
		if ((altura <= 0) && (imagenLogoWiki != null)) {
			altura = imagenLogoWiki.getHeight();
		}

		anchuraLogo = anchura;
		alturaLogo = altura;

		super.setSize(anchura, altura);
		setPreferredSize(new Dimension(anchura, altura));
	}

	/**
	 * Este método cambia la imagen del objeto
	 * 
	 * @param nomImagenObjeto Nombre fichero donde estï¿½ la imagen del objeto.
	 *                        Puede ser tambiï¿½n un nombre de recurso desde el
	 *                        paquete de esta clase. =======
	 * @param nomImagenLogo   Nombre fichero donde estï¿½ la imagen del objeto.
	 *                        Puede ser tambiï¿½n un nombre de recurso desde el
	 *                        paquete de esta clase.
	 */
	public void setImagen(String nomImagenLogo) {
		File futbolFile = new File(nomImagenLogo);
		URL imgURL = null;

		try {
			imgURL = futbolFile.toURI().toURL();
			if (!futbolFile.exists()) {
				imgURL = JLabelGraficoAjustado.class.getResource(nomImagenLogo).toURI().toURL();
			}
		} catch (Exception e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
		}

		if (imgURL == null) {
			imagenLogoWiki = null;
		} else {
			try {
				// Guarda el logo para posteriormente dibujarlo de manera escalada
				imagenLogoWiki = ImageIO.read(imgURL);
			} catch (IOException e) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			}
		}
		if (imagenLogoWiki == null) {

			setOpaque(true);
			setBackground(Color.BLACK);
			setForeground(Color.BLACK);
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setText(nomImagenLogo);

		}
		repaint();
	}

	/**
	 * Devuelve la anchura del rectangulo grafico del logo
	 *
	 * @return AnchuraLogo La anchura que tiene el logo
	 */
	public int getAnchuraLogo() {
		return anchuraLogo;
	}

	/**
	 * Devuelve la altura del rectangulo grafico del logo
	 *
	 * @return AlturaLogo La altura que tiene el logo
	 */
	public int getAlturaLogo() {
		return alturaLogo;
	}

	/**
	 * Devuelve la rotacion del logo
	 *
	 * @return RotacionLogo Rotacion actual del logo en radianes
	 */
	public double getRotacionLogo() {
		return radsRotacionLogo;
	}

	/**
	 * Modifica la rotacion del logo
	 *
	 * @param rotacionLogo Nueva rotacion del logo (en radianes)
	 */
	public void setRotacionLogo(double rotacionLogo) {
		radsRotacionLogo = rotacionLogo;
		repaint(); // Necesitamos el Repaint, sino, Swing no sabe que ha cambiado el dibujo
	}

	/**
	 * Devuelve la opacidad del logo
	 *
	 * @return OpacidadLogo Opacidad del logo (0.0f transparente a 1.0f opaco)
	 */
	public float getOpacidadLogo() {
		return opacidadLogo;
	}

	/**
	 * Modifica la opacidad del logo
	 *
	 * @param opacidad Opacidad del logo (0.0f transparente a 1.0f opaco)
	 */
	public void setOpacidadLogo(float opacidad) {
		if ((opacidad < 0.0f) || (opacidad > 1.0f)) {
			return;
		}
		this.opacidadLogo = opacidad;
		repaint(); // Necesitamos el Repaint, sino, Swing no sabe que ha cambiado el dibujo
	}

	/**
	 * Actualiza la posicion del objeto
	 *
	 * @param x Coordenada x (doble) - se redondea al pixel mas cercano
	 * @param y Coordenada y (doble) - se redondea al pixel mas cercano
	 */
	public void setLocation(double x, double y) {
		setLocation((int) Math.round(x), (int) Math.round(y));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (imagenLogoWiki != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int ancho = anchuraLogo;
			int alto = alturaLogo;
			int iniX = 0;
			int iniY = 0;

			if (ancho <= 0) {
				ancho = getWidth();
			} else {
				iniX = (getWidth() - ancho) / 2;
			}
			if (alto <= 0) {
				alto = getHeight();
			} else {
				iniY = (getHeight() - alto) / 2;
			}
			// Rotacion
			g2.rotate(radsRotacionLogo, getWidth() / 2, getHeight() / 2);

			// Transparencia
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidadLogo));

			g2.drawImage(imagenLogoWiki, iniX, iniY, ancho, alto, null);
		}
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		JFrame wikiFutbol = new JFrame("WikiLogo en JLabelGraficoAjustado");
		wikiFutbol.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabelGraficoAjustado label = new JLabelGraficoAjustado("resources/logo1.png", 100, 100);
		// En caso de: x<=0. Ajusta el ancho del logo.
		// En caso de: y<=0. Ajusta el alto del logo.
		wikiFutbol.setSize(1200, 700);
		// wikiFutbol.setLayout(null);
		wikiFutbol.setResizable(false);
		wikiFutbol.setLocationRelativeTo(null);
		wikiFutbol.add(label, BorderLayout.CENTER);
		wikiFutbol.setVisible(true);

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
		}

		for (int rotacion = 0; rotacion <= 250; rotacion++) {
			label.setRotacionLogo((rotacion * Math.PI) / 100);
			try {
				Thread.sleep(10); // Espera entre rotacion y rotacion
			} catch (Exception e) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			}
		}

		for (int opacidad = -100; opacidad <= 100; opacidad++) {
			label.setOpacidadLogo(Math.abs(opacidad * 0.01f));
			try {
				Thread.sleep(10); // Espera entre rotacion y rotacion
			} catch (Exception e) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			}
		}
	}

}